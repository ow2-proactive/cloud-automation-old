(function () {
    var services = angular.module('services', []);

    services.controller('ServicesCtrl', function ($http, $q, notificationService, $route, $scope, $modal, $timeout, $cookieStore) {
        var controller = this;
        this.services = [];
        this.autoRefresh = false;

        this.deleteService = function (service) {
            $scope.service = service
            var modalInstance = $modal.open({
                templateUrl: 'partials/service-delete.html',
                scope: $scope
            });

            modalInstance.result.then(function (service) {
                var config = { params: {status: 'done'}}
                $http.delete('/ca/api/occi/' + service.category + '/' + service.uuid, config).success(function (date) {
                    notificationService.success("Service " + service.uuid + " removed")
                    $route.reload()
                });
            }, function () {
                // nothing to do if modal closed
            });
        };

        this.refresh = function (autoRefresh) {
            console.log("called " + autoRefresh)
            this.autoRefresh = autoRefresh
            $cookieStore.put('autoRefresh', autoRefresh)
            if (controller.autoRefresh) {
                this.doRefresh(this.reschedule)
            }
        }

        this.reschedule = function () {
            if (controller.autoRefresh) {
                $timeout(function () {
                    controller.doRefresh(controller.reschedule)
                }, 2000);
            }
        }

        this.doRefresh = function (callback) {
            console.log(new Date())
            var controller = this;
            var computes = $http.get('/ca/api/occi/compute');
            var storages = $http.get('/ca/api/occi/platform');
            $q.all([computes, storages]).then(function (values) {
                controller.services = [];
                for (var i = 0; i < values.length; i++) {
                    controller.services = controller.services.concat(values[i].data.resources);
                }
                if (callback) {
                    callback()
                }
            });
        }

        this.doRefresh();
        this.refresh($cookieStore.get('autoRefresh') || false);
    });

    services.controller('ServiceDetailsCtrl', function ($http, $q, $routeParams) {
        var controller = this;
        this.compute = $http.get('/ca/api/occi/' + $routeParams.category + '/' + $routeParams.serviceId).
            success(function (data) {
                controller.service = data;
            });
    });

}());