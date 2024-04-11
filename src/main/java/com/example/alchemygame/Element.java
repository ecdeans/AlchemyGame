package com.example.alchemygame;

public class Element {

    private final String elementName;

    public Element(String elementName) {
        this.elementName = elementName;
    }

    public void reactOnCollision(Element otherElement) {
        // add reaction here, this called when elements collide
    }

    public String getElementName() {
        return this.elementName;
    }

}
