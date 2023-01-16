import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Mediator {
    Vector<MyDrawing> drawings;// 描画中の図形
    MyCanvas canvas;// キャンバス
    Vector<MyDrawing> selectedDrawings = new Vector<MyDrawing>();// 選択中の図形
    JMenuItem colorItem;
    Vector<MyDrawing> buffers = new Vector<MyDrawing>();
    Vector<Integer> xps = new Vector<Integer>(128);
    Vector<Integer> yps = new Vector<Integer>(128);
    MyRectangle selectRegion;

    public Mediator(MyCanvas canvas) {
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
    }

    public Enumeration<MyDrawing> drawingsElements() {
        return drawings.elements();
    }

    public void addDrawing(MyDrawing d) {
        drawings.add(d);
    }

    public void removeDrawing(MyDrawing d) {
        drawings.remove(d);
    }

    public void move(int dx, int dy) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                d.move(dx, dy);
            }
        }
        repaint();
    }

    public void repaint() {
        canvas.repaint();
    }

    public void setSelectedDrawings(MyDrawing d) {
        d.setSelected(true);
        selectedDrawings.add(d);
        repaint();
    }

    public void unSelected() {
        for(MyDrawing d:selectedDrawings) {
            d.setSelected(false);
        }
        this.selectedDrawings.clear();
    }

    public JMenuItem getSource() {
        return this.colorItem;
    }

    public void setLineColors(Color c) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                d.lineColor = c;
            }
        }
    }

    public void setFillColors(Color c) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                d.fillColor = c;
            }
        }
        repaint();
    }

    public void setLineWidthes(int lineWidth) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                d.setLineWidth(d.getLineWidth()+lineWidth);
            }
        }
        repaint();
    }

    public void setBlightness(int blightness) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                d.setBlightness(d.blightness+blightness);
            }
        }
        repaint();
    }

    public void clearBuffer() {
        buffers.clear();
    }

    public void copy() {
        clearBuffer();
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                buffers.add(d);
                xps.add(d.getX());
                yps.add(d.getY());
            }
        }
    }

    public void cut() {
        clearBuffer();
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                buffers.add(d.clone());
                removeDrawing(d);
            }
        }
        unSelected();
        repaint();
    }

    public void paste() {
        if(buffers != null) {
            MyDrawing clone;
            for(MyDrawing b:buffers) {
                clone = (MyDrawing)b.clone();
                clone.setLocation(b.getX()+10, b.getY()+10);
                clone.setSelected(false);
                addDrawing(clone);
                repaint();
            }
        }
    }

    public boolean checkNull() {
        return selectedDrawings == null;
    }

    public void setShadow() {
        if(!checkNull()) {
            for(MyDrawing d:selectedDrawings) {
                if(!d.hasShadow) {
                    d.setShadow(true);
                } else {
                    d.setShadow(false);
                }
            }
        }
    }

    public void setDashed(boolean isDashed) {
        if(!checkNull()) {
            for(MyDrawing d:selectedDrawings) {
                d.setDashed(isDashed);
            }
        }
    }

    // 四角 by 内に drawings が含まれているなら selectedDrawings に追加
    public void selectDrawings(MyRectangle by) {
        unSelected();

        Enumeration<MyDrawing> d = drawingsElements();
        while(d.hasMoreElements()) {
            MyDrawing e = d.nextElement();
            // boolean contain = new Area(e.region).intersects(by.region.getBounds2D());
            if(by.getX() <= e.getX() && by.getY() <= e.getY() && by.getX()+by.getW() >= e.getX()+e.getW() && by.getY()+by.getH() >= e.getY()+e.getH()) {
                setSelectedDrawings(e);
            }
        }
    }

    // 選択領域の四角をセットする
    public void setSelectRectangle(MyRectangle rectangle) {
        canvas.selectRegion = rectangle;
    }


    public void removeSelectedDrawings(Vector<MyDrawing> selectedDrawings) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                removeDrawing(d);
            }
            unSelected();
        }
        repaint();
    }

    public void setLayer(Vector<MyDrawing> selectedDrawings) {
        if(!(checkNull())) {
            for(MyDrawing d:selectedDrawings) {
                drawings.remove(d);
                drawings.add(d);
            }
        }
        repaint();
    }
}