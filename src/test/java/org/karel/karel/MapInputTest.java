package org.karel.karel;

import org.karel.karel.karelmap.Direction;
import org.karel.karel.karelmap.InvalidMapException;
import org.karel.karel.karelmap.KarelMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MapInputTest {
    @Test
    public void emptyMap(){
        KarelMap map = new KarelMap("5 5 1 1 E 0");
        Assertions.assertEquals(1, map.getX());
        Assertions.assertEquals(1, map.getY());
        Assertions.assertEquals(Direction.EAST, map.getDirection());
        Assertions.assertEquals(0, map.getBag());
    }

    @Test
    public void wrongSize(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("0 5 1 1 E 0"));
    }

    @Test
    public void wrongPosition1(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 -1 E 0"));
    }

    @Test
    public void wrongPosition2(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 7 1 E 0"));
    }

    @Test
    public void wrongDirection(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 A 0"));
    }

    @Test
    public void wrongBag(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E -5"));
    }

    @Test
    public void wrongTypeWB(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E 5     E 4 5 W"));
    }

    @Test
    public void wrongWall(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E 5     W 4 5"));
    }

    @Test
    public void wrongWallDirection(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E 5     W 4 5 A"));
    }

    @Test
    public void wrongBeepers(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E 5     B 4 5 -5"));
    }

    @Test
    public void wrongBeepersPosition(){
        Assertions.assertThrows(InvalidMapException.class, () -> new KarelMap("5 5 1 1 E 5     B 4 6 1"));
    }

    @Test
    public void walls(){
        KarelMap map = new KarelMap("5 5 1 1 E 0    W 2 2 W");
        Assertions.assertTrue(map.hasWall(2, 2, Direction.WEST));
    }

    @Test
    public void beepers(){
        KarelMap map = new KarelMap("5 5 1 1 E 0    B 2 2 8");
        Assertions.assertEquals(8, map.getBeepersMap().getBeepersCount(2, 2));
    }


}
