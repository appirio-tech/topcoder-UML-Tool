/*
 * Copyright (C) 2007 TopCoder Inc., All Rights Reserved.
 */
package com.topcoder.uml.actions.diagram;

import com.topcoder.uml.model.core.Element;

/**
 * <p>
 * This class is a subclass of {@link CreateDiagramAction}.
 * </p>
 *
 * <p>
 * This action will create a class diagram with no contained elements.
 * <p>
 *
 * <p>This class will be created by application directly.</p>
 *
 * <p>
 * <b>Thread Safety:</b>
 * This class is not thread safe by containing mutable state information.
 * </p>
 *
 * @author kinzz, TCSDEVELOPER
 * @version 1.0
 */
public class CreateClassDiagramAction extends CreateDiagramAction {

    /**
     * <p>
     * Represents the type of the diagram.
     * </p>
     */
    /*
     * BugFix: UML-9507
     */
//    public static final String TYPE = "Class";
    public static final String TYPE = "ClassDiagram";

    /**
     * <p>
     * Constructor of the create class diagram action.
     * </p>
     *
     * @param owner the possible null owner of the diagram
     * @param name the possible null, possible empty name of the diagram
     *
     * @throws IllegalStateException if the diagram can't be created or can't be formatted
     */
    public CreateClassDiagramAction(Element owner, String name) {
        super(TYPE, owner, name);
    }
}
