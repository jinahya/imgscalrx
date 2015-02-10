/*
 * Copyright 2015 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.imgscalr;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import static org.imgscalr.Scalr.Method;
import static org.imgscalr.Scalr.Mode;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public strictfp class Scalr extends org.imgscalr.Scalr {


    /**
     * Resizes specified image preserving the proportion and relocates by given
     * arguments.
     *
     * @param sourceImage the source image to resize.
     * @param scalingMethod the scaling method.
     * @param targetWidth the target width.
     * @param targetHeight the target height.
     * @param magnificationFactor the magnification factor. {@code 1.0f} for
     * fitting into the box, {@code 1.0f} for fit to max width or height.
     * @param horizontalWeigth the horizontal weight. {@code 1.0f} for aligning
     * to the left align, {@code 1.0f} for aligning to the right.
     * @param verticalWeight the vertical weight. {@code 1.0f} for aligning to
     * the top, {@code 1.0f} for aligning to the bottom.
     * @param ops
     *
     * @return a resized image.
     */
    public static BufferedImage resize(final BufferedImage sourceImage,
                                       final Method scalingMethod,
                                       final int targetWidth,
                                       final int targetHeight,
                                       final float magnificationFactor,
                                       final float horizontalWeigth,
                                       final float verticalWeight,
                                       final BufferedImageOp... ops) {

        final int sourceWidth = sourceImage.getWidth();
        final int sourceHeight = sourceImage.getHeight();

        final double widthRatio = targetWidth / (double) sourceWidth;
        final double heightRatio = targetHeight / (double) sourceHeight;

        final double minRatio = Math.min(widthRatio, heightRatio);
        final double maxRatio = Math.max(widthRatio, heightRatio);

        final int auxiliaryWidth
            = (int) (magnificationFactor == .0f
                     ? sourceWidth * minRatio
                     : (sourceWidth * maxRatio) * magnificationFactor);
        final int auxiliaryHeight
            = (int) (magnificationFactor == .0f
                     ? sourceHeight * minRatio
                     : (sourceHeight * maxRatio) * magnificationFactor);

//        final int imageType
//            = auxiliaryImage.getTransparency() == Transparency.OPAQUE
//              ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        final int imageType = BufferedImage.TYPE_INT_ARGB;
        final BufferedImage targetImage = new BufferedImage(
            targetWidth, targetHeight, imageType);

        final BufferedImage auxiliaryImage = resize(
            sourceImage, scalingMethod, Mode.FIT_EXACT, auxiliaryWidth,
            auxiliaryHeight);

        final int x = (int) ((targetWidth - auxiliaryWidth) * horizontalWeigth);
        final int y = (int) ((targetHeight - auxiliaryHeight) * verticalWeight);
        final Graphics2D graphics = targetImage.createGraphics();
        graphics.drawImage(auxiliaryImage, x, y, null);
        graphics.dispose();

        auxiliaryImage.flush();

        return targetImage;
    }


    /**
     *
     * @param sourceImage
     * @param scalingMethod
     * @param targetWidth
     * @param targetHeight
     * @param horizontalWeigth
     * @param verticalWeight
     * @param ops
     *
     * @return a resized image.
     *
     * @see #resize(java.awt.image.BufferedImage, org.imgscalr.Scalr.Method,
     * int, int, float, float, float, java.awt.image.BufferedImageOp...)
     */
    public static BufferedImage resizeInbox(final BufferedImage sourceImage,
                                            final Method scalingMethod,
                                            final int targetWidth,
                                            final int targetHeight,
                                            final float horizontalWeigth,
                                            final float verticalWeight,
                                            final BufferedImageOp... ops) {

        return resize(sourceImage, scalingMethod, targetWidth, targetHeight,
                      .0f, horizontalWeigth, verticalWeight, ops);
    }


    /**
     *
     * @param sourceImage
     * @param scalingMethod
     * @param targetWidth
     * @param targetHeight
     * @param ops
     *
     * @return
     *
     * @see #resizeInbox(java.awt.image.BufferedImage,
     * org.imgscalr.Scalr.Method, int, int, float, float,
     * java.awt.image.BufferedImageOp...)
     */
    public static BufferedImage resizeInboxUpperLeft(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeInbox(sourceImage, scalingMethod, targetWidth,
                           targetHeight, .0f, .0f, ops);
    }


    /**
     *
     * @param sourceImage
     * @param scalingMethod
     * @param targetWidth
     * @param targetHeight
     * @param ops
     *
     * @return
     *
     * @see #resizeInbox(java.awt.image.BufferedImage,
     * org.imgscalr.Scalr.Method, int, int, float, float,
     * java.awt.image.BufferedImageOp...)
     */
    public static BufferedImage resizeInboxCenter(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeInbox(sourceImage, scalingMethod, targetWidth,
                           targetHeight, .5f, .5f, ops);
    }


    /**
     *
     * @param sourceImage
     * @param scalingMethod
     * @param targetWidth
     * @param targetHeight
     * @param ops
     *
     * @return
     *
     * @see #resizeInbox(java.awt.image.BufferedImage,
     * org.imgscalr.Scalr.Method, int, int, float, float,
     * java.awt.image.BufferedImageOp...)
     */
    public static BufferedImage resizeInboxLowerRight(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeInbox(sourceImage, scalingMethod, targetWidth,
                           targetHeight, 1.0f, 1.0f, ops);
    }


    public static BufferedImage resizeOutbox(final BufferedImage sourceImage,
                                             final Method scalingMethod,
                                             final int targetWidth,
                                             final int targetHeight,
                                             final float horizontalWeigth,
                                             final float verticalWeight,
                                             final BufferedImageOp... ops) {

        return resize(sourceImage, scalingMethod, targetWidth, targetHeight,
                      1.0f, horizontalWeigth, verticalWeight, ops);
    }


    public static BufferedImage resizeOutboxUpperLeft(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeOutbox(sourceImage, scalingMethod, targetWidth,
                            targetHeight, .0f, .0f, ops);
    }


    public static BufferedImage resizeOutboxCenter(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeOutbox(sourceImage, scalingMethod, targetWidth,
                            targetHeight, .5f, .5f, ops);
    }


    public static BufferedImage resizeOutboxLowerRight(
        final BufferedImage sourceImage, final Method scalingMethod,
        final int targetWidth, final int targetHeight,
        final BufferedImageOp... ops) {

        return resizeOutbox(sourceImage, scalingMethod, targetWidth,
                            targetHeight, 1.0f, 1.0f, ops);
    }


    private Scalr() {

        super();
    }


}

