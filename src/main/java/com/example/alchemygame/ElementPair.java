package com.example.alchemygame;

class ElementPair {
    private Element a, b;
    public ElementPair(Element src, Element dest) {
        this.a = src; this.b = dest;
    }

    public boolean intersects() {
        if (a == b) return false;
        a.intersects(b.getBoundsInLocal());
        return a.getLayoutBounds().intersects(b.getLayoutBounds());
    }

    public Element getA() {
        return a;
    }

    public Element getB() {
        return b;
    }

    @Override public String toString() {
        return a.getId() + " : " + b.getId();
    }
}
