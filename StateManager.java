public class StateManager{
    State state;
    boolean isDashed;
    boolean hasShadow;
    Mediator mediator;


    public StateManager (Mediator mediator){
        this.mediator = mediator;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addDrawing(MyDrawing d) {
        d.setDashed(isDashed);
        mediator.addDrawing(d);
    }

    public void addDrawing(MyRectangle r) {
        r.setDashed(isDashed);
        mediator.addDrawing(r);
    }

    public void addDrawing(MyOval o) {
        o.setDashed(isDashed);
        mediator.addDrawing(o);
    }

    public void addDrawing(MyHendecagonal h) {
        h.setDashed(isDashed);
        mediator.addDrawing(h);
    }

    public void addDrawing(MyTsukuba tsukuba) {
        tsukuba.setDashed(isDashed);
        mediator.addDrawing(tsukuba);
    }

    public void addDrawing(MySelect select) {
        mediator.addDrawing(select);
    }

    public void removeDrawing(MyDrawing d) {
        mediator.removeDrawing(d);
    }

    public void removeDrawing(MyRectangle r) {
        mediator.removeDrawing(r);
    }

    public void removeDrawing(MyOval o) {
        mediator.removeDrawing(o);
    }

    public void removeDrawing(MyHendecagonal h) {
        mediator.removeDrawing(h);
    }

    public void removeDrawing(MyTsukuba tsukuba) {
        mediator.removeDrawing(tsukuba);
    }

    public void mouseDown(int x, int y) {
        state.mouseDown(x, y);
        mediator.repaint();
    }

    public void mouseUp(int x, int y) {
        state.mouseUp(x, y);
        mediator.repaint();
    }

    public void mouseDrag(int x, int y) {
        state.mouseDrag(x, y);
        mediator.repaint();
    }
}