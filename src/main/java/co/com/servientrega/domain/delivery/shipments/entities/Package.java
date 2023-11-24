package co.com.servientrega.domain.delivery.shipments.entities;

import co.com.servientrega.domain.delivery.common.values.Weight;
import co.com.servientrega.domain.delivery.shipments.identity.PackageId;
import co.com.servientrega.domain.delivery.shipments.values.PackageDescription;
import co.com.servientrega.domain.delivery.shipments.values.PackageName;
import co.com.servientrega.domain.delivery.common.values.Size;
import co.com.sofka.domain.generic.Entity;

public class Package extends Entity<PackageId> {
    private PackageName name;
    private PackageDescription description;
    private Weight weight;
    private Size size;

    public Package(PackageId entityId, PackageName name, PackageDescription description, Weight weight, Size size) {
        super(entityId);
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.size = size;
    }

    public Package(PackageName name, PackageDescription description, Weight weight, Size size) {
        this(new PackageId(), name, description, weight, size);
    }

    public void changeName(PackageName name) {
        this.name = name;
    }

    public void changeDescription(PackageDescription description) {
        this.description = description;
    }

    public void changeWeight(Weight weight) {
        this.weight = weight;
    }

    public void changeSize(Size size) {
        this.size = size;
    }

    public PackageName packageName() {
        return name;
    }

    public PackageDescription packageDescription() {
        return description;
    }

    public Weight packageWeight() {
        return weight;
    }

    public Size packageSize() {
        return size;
    }
}
