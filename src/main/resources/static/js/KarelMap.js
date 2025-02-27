import {WallsMap} from "./WallsMap.js";
import {BeepersMap} from "./BeepersMap.js";
import {RobotPosition} from "./RobotPosition.js";

export class KarelMap {
    constructor(wallsMap, beepersMap, robotPosition) {
        this.wallsMap = wallsMap;
        this.beepersMap = beepersMap;
        this.robotPosition = robotPosition;
    }

    static fromParams(rows, cols, x, y, bag, direction, beepers = new Map(), walls = new Set()) {
        return new KarelMap(new WallsMap(rows, cols, walls), new BeepersMap(beepers), new RobotPosition(x, y, direction, bag));
    }

    static fromFile(fileContent) {
        console.log("From File");
        const input = fileContent.split(/\s+/);
        let index = 0;

        const nextInt = () => {
            if (index >= input.length || isNaN(parseInt(input[index]))) throw new Error("InvalidMapException");
            console.log("nextInt");
            return parseInt(input[index++]);
        };

        const nextString = () => {
            if (index >= input.length) throw new Error("InvalidMapException");
            console.log("nextString");
            return input[index++];
        };

        const rows = nextInt();
        const cols = nextInt();
        const x = nextInt();
        const y = nextInt();
        const dir = nextString();
        const direction = { 'N': 'NORTH', 'E': 'EAST', 'S': 'SOUTH', 'W': 'WEST' }[dir];
        if (!direction) throw new Error("InvalidMapException");

        const bag = nextInt();
        if (bag < 0 || bag >= 100) throw new Error("InvalidMapException");

        const walls = new Set();
        const beepers = new Map();

        while (index < input.length) {
            const type = nextString();
            const a = nextInt();
            const b = nextInt();
            console.log(`Coordinates : ${a}, ${b}`);
            if (type === "B") {
                const beep = nextInt();
                if (beep < 0 || beep >= 100) throw new Error("InvalidMapException");
                console.log(`beepers ${beep}`);
                beepers.set(`${a},${b}`, beep);
            } else if (type === "W") {
                const d = nextString();
                console.log(`wall ${d}`);
                if (!["N", "E", "S", "W"].includes(d)) throw new Error("InvalidMapException");
                walls.add(`${a},${b},${d}`);
            } else {
                throw new Error("InvalidMapException");
            }
        }

        return new KarelMap(new WallsMap(rows, cols, walls), new BeepersMap(beepers), new RobotPosition(x, y, direction, bag));
    }

    hasWall(x, y, direction) {
        return this.wallsMap.hasWall(x, y, direction);
    }

    getBeepersCount() {
        return this.beepersMap.getBeepersCount(this.robotPosition.getX(), this.robotPosition.getY());
    }

    toString() {
        return `KarelMap(Position: (${this.robotPosition.x}, ${this.robotPosition.y}), Direction: ${this.robotPosition.direction}, Bag: ${this.robotPosition.bag})`;
    }
}