export class RobotPosition {
    constructor(x, y, direction, bag) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.bag = bag;
    }

    getX() {
        return this.x;
    }

    getY() {
        return this.y;
    }

    getDirection() {
        return this.direction;
    }

    getBag() {
        return this.bag;
    }

    equals(other) {
        if (!(other instanceof RobotPosition)) return false;
        return this.bag === other.bag && JSON.stringify(this.position) === JSON.stringify(other.position);
    }

    hashCode() {
        return JSON.stringify(this.position) + this.bag;
    }
}