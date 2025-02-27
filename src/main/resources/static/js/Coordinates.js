export class Coordinates {
    constructor(x, y, direction = 'NORTH') {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    getLeft() {
        const directions = ['NORTH', 'WEST', 'SOUTH', 'EAST'];
        const newDirection = directions[(directions.indexOf(this.direction) + 1) % 4];
        return new Coordinates(this.x, this.y, newDirection);
    }

    getNext() {
        switch (this.direction) {
            case 'EAST': return new Coordinates(this.x + 1, this.y, 'EAST');
            case 'SOUTH': return new Coordinates(this.x, this.y - 1, 'SOUTH');
            case 'WEST': return new Coordinates(this.x - 1, this.y, 'WEST');
            case 'NORTH': return new Coordinates(this.x, this.y + 1, 'NORTH');
        }
    }

    toNorth() {
        return new Coordinates(this.x, this.y, 'NORTH');
    }

    getRight() {
        return this.getLeft().getLeft().getLeft();
    }

    equals(other) {
        if (!(other instanceof Coordinates)) return false;
        return this.x === other.x && this.y === other.y && this.direction === other.direction;
    }

    hashCode() {
        return `${this.x},${this.y},${this.direction}`;
    }
}