package controller;

import album.IShapeModel;
import album.ShapeModel;

/**
 * The interface Controller.
 */
public interface IController {
    /**
     * Go.
     *
     * @param model the model
     * @throws Exception the exception
     */
    void go(IShapeModel model) throws Exception;
}
