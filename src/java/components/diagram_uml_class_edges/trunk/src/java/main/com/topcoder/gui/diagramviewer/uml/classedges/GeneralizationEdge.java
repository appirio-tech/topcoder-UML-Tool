/*
 * Copyright (C) 2007 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.gui.diagramviewer.uml.classedges;

import com.topcoder.diagraminterchange.GraphEdge;
import com.topcoder.diagraminterchange.Uml1SemanticModelBridge;

import com.topcoder.gui.diagramviewer.edges.LineStyle;

import com.topcoder.uml.model.core.relationships.Generalization;

/**
 * <p>
 * This class is the concrete Edge of this component. It is an extension of {@link BaseEdge} class. The required
 * information is retrieved from the <code>Generalization</code> class from the UML Model and from the
 * <code>GraphEdge</code> associated with it. No active edge endings are supported. The class uses a continuous line,
 * an open ending and the closed arrow (triangle) ending.
 * </p>
 *
 * <p>
 * This class implement creating of the concrete line style for the edge and the concrete edge endings (with
 * compartments). The drawing of the edge is implemented in the parent Edge class (in the Diagram Edges component). The
 * edge endings (and their compartments) painted automatically as child graphical components.
 * </p>
 *
 * <p>
 * <b>Thread Safety:</b> This class is mutable, and not thread-safe.
 * </p>
 *
 * @author MiG-29, littlebull
 * @version 1.0
 */
public class GeneralizationEdge extends BaseEdge {

    /** Represent the default dash length. */
    private static final int DEFAULT_DASH_LENGTH = 5;

    /** Represent the default blank length. */
    private static final int DEFAULT_BLANK_LENGTH = 0;

    /**
     * <p>
     * Constructor of the concrete edge instance.
     * </p>
     *
     * @param graphEdge
     *            the graphEdge representing this Edge.
     *
     * @throws IllegalArgumentException
     *             If graphEdge is null
     * @throws IllegalGraphElementException
     *             if any problem with retrieving data from related graphEdge occurs
     */
    public GeneralizationEdge(GraphEdge graphEdge) throws IllegalGraphElementException {
        super(graphEdge, new LineStyle(DEFAULT_DASH_LENGTH, DEFAULT_BLANK_LENGTH), null, new ClosedArrow());
    }

    /**
     * <p>
     * Reload diagram interchange information from contained graphEdge.
     * </p>
     *
     * @param message
     *            message telling how the <tt>GraphNode</tt> is changed
     *
     * @throws IllegalArgumentException
     *             if <tt>message</tt> is null
     * @throws IllegalGraphElementException
     *             if the structure of <tt>GraphNode</tt> is invalid
     */
    public void notifyGraphEdgeChange(String message) throws IllegalGraphElementException {
        Utils.checkObjectNotNull(message, "message");

        // NOTE: we should update the text firstly
        try {
            //Retrieve the Generalization element
            Uml1SemanticModelBridge semanticModelBridge =
                (Uml1SemanticModelBridge) this.getGraphEdge().getSemanticModel();
            Generalization generalization = (Generalization) semanticModelBridge.getElement();

            //Update name, stereotype compartments' text
            getNameCompartment().setText(generalization.getName());
            getStereotypeCompartment().setText(Utils.retrieveStereotypeText(generalization.getStereotypes()));

        } catch (NullPointerException e) {
            throw new IllegalGraphElementException(getGraphEdge(), "expects a correct semantic model", e);
        } catch (ClassCastException e) {
            throw new IllegalGraphElementException(getGraphEdge(), "the element should be instance of Generalization",
                    e);
        }

        //Resize and relocate name, stereotype compartments
        Utils.updateTextFieldSizeLocationVisibility(getNameCompartment());
        Utils.updateTextFieldSizeLocationVisibility(getStereotypeCompartment());

    }
}
