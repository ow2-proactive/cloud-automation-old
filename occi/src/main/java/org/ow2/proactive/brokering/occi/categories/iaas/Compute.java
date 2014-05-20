package org.ow2.proactive.brokering.occi.categories.iaas;

import java.util.ArrayList;
import java.util.List;

import org.ow2.proactive.brokering.occi.Attribute;
import org.ow2.proactive.brokering.occi.Category;

public class Compute implements Category {

    public Compute() {

    }

    public List<Attribute> getSpecificAttributeList() {
        List<Attribute> attributeList = new ArrayList<Attribute>();
        boolean mutable = true;
        boolean required = true;
        attributeList.add(new Attribute("sla", mutable, !required, "silver"));
        attributeList.add(new Attribute("occi.compute.architecture", !mutable, !required));
        attributeList.add(new Attribute("occi.compute.cores", mutable, !required));
        attributeList.add(new Attribute("occi.compute.hostname", !mutable, !required));
        attributeList.add(new Attribute("occi.compute.memory", mutable, !required));
        attributeList.add(new Attribute("occi.compute.state", mutable, !required, "inactive"));
        attributeList.add(new Attribute("occi.compute.localstorage", !mutable, !required, "20"));
        attributeList.add(new Attribute("occi.compute.organization.name", mutable, !required));
        attributeList.add(new Attribute("occi.compute.vendor.location", mutable, !required));
        attributeList.add(new Attribute("occi.compute.vendor.uuid", mutable, !required));
        attributeList.add(new Attribute("occi.compute.vendor.vmpath", mutable, !required));
        attributeList.add(new Attribute("occi.compute.template_name", !mutable, required));
        attributeList.add(new Attribute("occi.compute.password", mutable, !required));
        attributeList.add(new Attribute("occi.compute.lease.stop", mutable, !required, "-1"));
        attributeList.add(new Attribute("occi.compute.lease.stop.warning", mutable, !required, "-1"));
        attributeList.add(new Attribute("occi.compute.lease.delete", mutable, !required, "-1"));
        attributeList.add(new Attribute("occi.compute.lease.delete.warning", mutable, !required, "-1"));
        attributeList.add(new Attribute("occi.networkinterface.address", mutable, !required));
        attributeList.add(new Attribute("occi.compute.proactive.node.token", mutable, !required));
        attributeList.add(new Attribute("occi.compute.error.code", mutable, !required, "0"));
        attributeList.add(new Attribute("occi.compute.error.description", mutable, !required));
        attributeList.add(new Attribute("occi.error.description", mutable, !required));
        attributeList.add(new Attribute("proactive.node.url", mutable, !required));
        return attributeList;
    }
}
