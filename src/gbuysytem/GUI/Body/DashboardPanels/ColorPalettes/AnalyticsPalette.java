package gbuysytem.GUI.Body.DashboardPanels.ColorPalettes;

import java.awt.Color;

public enum AnalyticsPalette {
    CUSTOMER("#326BFE"),
    REVENUE("#00B0FA"),
    INVOICE("#FDB407"),
    PROFIT("#FF7400");

    Color color;
    String hex;

    AnalyticsPalette(String hex){
        this.hex = hex;
        color = Color.decode(hex);
    }

    public Color getColor(){
        return color;
    }
}
