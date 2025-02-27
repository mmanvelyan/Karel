export class BeepersMap {
    constructor(beepers = new Map()) {
        this.beepers = beepers;
    }

    getBeepersCount(x, y) {
        return this.beepers.get(`${x},${y}`) || 0;
    }

    getBeepersCountByCoordinates(coordinates) {
        return this.getBeepersCount(coordinates.x, coordinates.y);
    }

    getBeepers() {
        return new Map(this.beepers);
    }

    equals(other) {
        if (!(other instanceof BeepersMap)) return false;
        return this.beepers.size === other.beepers.size && [...this.beepers.entries()].every(([key, value]) => other.beepers.get(key) === value);
    }

    hashCode() {
        return JSON.stringify([...this.beepers]);
    }
}
