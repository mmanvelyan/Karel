export class WallsMap {
    constructor(rows, cols, walls = new Set()) {
        this.rows = rows;
        this.cols = cols;
        this.walls = walls;
    }

    hasWall(x, y, direction) {
        switch (direction) {
            case 'NORTH':
                return y === this.rows || this.walls.has(`${x},${y},N`) || this.walls.has(`${x},${y+1},S`);
            case 'EAST':
                return x === this.cols || this.walls.has(`${x},${y},E`) || this.walls.has(`${x+1},${y},W`);
            case 'SOUTH':
                return y === 1 || this.walls.has(`${x},${y},S`) || this.walls.has(`${x},${y-1},N`);
            case 'WEST':
                return x === 1 || this.walls.has(`${x},${y},W`) || this.walls.has(`${x-1},${y},E`);
            default:
                return false;
        }
    }

    getRows() {
        return this.rows;
    }

    getCols() {
        return this.cols;
    }

    equals(other) {
        if (!(other instanceof WallsMap)) return false;
        return this.rows === other.rows && this.cols === other.cols && this.walls.size === other.walls.size && [...this.walls].every(wall => other.walls.has(wall));
    }

    hashCode() {
        return `${this.rows},${this.cols},${Array.from(this.walls).join('|')}`;
    }
}
