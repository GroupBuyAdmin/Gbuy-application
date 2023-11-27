package gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.TestTable;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gbuysytem.GUI.Body.DashboardPanels.newProductsPanel.SingleProduct;

public class TestTable {

    private JPanel masterPanel;

    public TestTable(){
        List<SingleProduct> products = new ArrayList<>();

        SingleProduct p1 = new SingleProduct("shoe", "$10");
        SingleProduct p2 = new SingleProduct("shirt", "$13");
        SingleProduct p3 = new SingleProduct("vape", "$20");
        SingleProduct p4 = new SingleProduct("shabu", "$143");
        SingleProduct p5 = new SingleProduct("electric fan", "$220");


        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);


        // JTable table = new JTable(
        //     new Object[][]{
        //         new Product[]{products.get(0)},
        //         new Product[]{products.get(1)},
        //         new Product[]{products.get(2)},
        //     },
        //     new String[]{"header"}
        // );

        JTable table = new JTable(new ProductFeedTableModel((ArrayList<SingleProduct>) products));
        table.setDefaultRenderer(SingleProduct.class, new ProductFeedCellRenderer());
        table.setRowHeight(60);


        masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(new JScrollPane(table));

        



    }

    public void testPanel(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.add(masterPanel, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        TestTable t = new TestTable();
        t.testPanel();
    }
}
