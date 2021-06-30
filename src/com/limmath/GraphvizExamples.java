package com.limmath;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;

/**
 * Примеры из описания graphviz-java:
 *   https://github.com/nidi3/graphviz-java
 */
public class GraphvizExamples {

    public static String ex4() throws IOException {
        MutableGraph g = new Parser().read(new File("./files/input/dijkstra.dot"));

        Graphviz gv1 = Graphviz.fromGraph(g).width(700);
        try {
            gv1.render(Format.PNG).toFile(new File("./files/output/ex4.1.png"));
            gv1.render(Format.SVG).toFile(new File("./files/output/ex4.1.svg"));
        } catch (Exception ignored) {
        }

        g.graphAttrs()
            .add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90))
            .nodeAttrs().add(Color.WHITE.fill())
            .nodes().forEach(node
                -> node.add(
                Color.named(node.name().toString()),
                Style.lineWidth(4).and(Style.FILLED)));

        Graphviz gv2 = Graphviz.fromGraph(g).width(700);
        try {
            gv2.render(Format.PNG).toFile(new File("./files/output/ex4.2.png"));
            gv2.render(Format.SVG).toFile(new File("./files/output/ex4.2.svg"));
        } catch (Exception ignored) {
        }
        return gv2.render(Format.SVG).toString();
    }
}
