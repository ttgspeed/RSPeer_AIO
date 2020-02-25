package io.stricker.config;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

public class Areas {
    public final static Area WINTERTODT_AREA = Area.rectangular(1607, 3966, 1652, 4029);

    public static Area BRUMA_AREA = Area.absolute(new Position(1622, 3988));
    public static Area BRUMA_AREA_ALT = Area.absolute(new Position(1638, 3988));

    public static Area BRAZIER_AREA = Area.absolute(new Position(1622, 3996));
    public static Area BRAZIER_AREA_ALT = Area.absolute(new Position(1638, 3996));

    public static void swapAreas(){
        Area braz_tmp = BRAZIER_AREA;
        Area brum_tmp = BRUMA_AREA;

        BRAZIER_AREA = BRAZIER_AREA_ALT;
        BRUMA_AREA = BRUMA_AREA_ALT;

        BRAZIER_AREA_ALT = braz_tmp;
        BRUMA_AREA_ALT = brum_tmp;
    }
}
