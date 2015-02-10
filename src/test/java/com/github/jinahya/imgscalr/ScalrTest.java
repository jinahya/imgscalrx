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


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr.Method;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class ScalrTest {


    @Test(enabled = false)
    public void maginifictionFactor() throws IOException {

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            final BufferedImage targetImage = Scalr.resize(
                sourceImage, Method.SPEED, 100, 100, .0f, .5f, .5f);

            targetImage.flush();
            sourceImage.flush();
        }
    }


    @Test(enabled = true)
    public void resizeUpperLeftToLowerRight() throws IOException {

        final File dir = new File("target/resized/UpperLeftToLowerRight");
        if (!dir.isDirectory()) {
            assertTrue(dir.mkdirs());
        }

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            final int targetWidth = sourceImage.getWidth() / 4;
            final int targetHeight = sourceImage.getHeight() / 2;
            float horizontalWeight = .0f;
            float verticalWeight = .0f;
            for (int i = 0; i < 20; i++) {
                final BufferedImage targetImage = Scalr.resize(
                    sourceImage, Method.SPEED, targetWidth, targetHeight,
                    1.0f, horizontalWeight, verticalWeight);
                ImageIO.write(
                    targetImage, "png", new File(
                        dir, name + "_upper_left_to_lower_right_"
                             + String.format("%1$.2f", horizontalWeight) + "x"
                             + String.format("%1$.2f", verticalWeight)
                             + ".png"));
                targetImage.flush();
                horizontalWeight += .05f;
                verticalWeight += .05f;
            }
            sourceImage.flush();
        }

        {
            final String name = "1920x1080.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            final int targetWidth = sourceImage.getWidth() / 2;
            final int targetHeight = sourceImage.getHeight() / 4;
            float horizontalWeight = .0f;
            float vertialWeight = .0f;
            for (int i = 0; i < 20; i++) {
                final BufferedImage targetImage = Scalr.resize(
                    sourceImage, Method.SPEED, targetWidth, targetHeight,
                    1.0f, horizontalWeight, vertialWeight);
                ImageIO.write(
                    targetImage, "png", new File(
                        dir, name + "_upper_left_to_lower_right_"
                             + String.format("%1$.2f", horizontalWeight) + "x"
                             + String.format("%1$.2f", vertialWeight)
                             + ".png"));
                targetImage.flush();
                horizontalWeight += .05f;
                vertialWeight += .05f;
            }
            sourceImage.flush();
        }
    }


    @Test(enabled = true)
    public void resizeInboxUpperLeft() throws IOException {

        final File dir = new File("target/resized/InboxUpperLeft");
        if (!dir.isDirectory()) {
            assertTrue(dir.mkdirs());
        }

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxUpperLeft(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_upper_left_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetWidth += 100;
                targetHeight += 100;
                targetImage.flush();
            }
            sourceImage.flush();
        }

        {
            final String name = "1920x1080.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxUpperLeft(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_upper_left_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetImage.flush();
                targetWidth += 100;
                targetHeight += 100;
            }
            sourceImage.flush();
        }
    }


    @Test(enabled = false)
    public void resizeInboxCenter() throws IOException {

        final File dir = new File("target/resized/InboxCenter");
        if (!dir.isDirectory()) {
            assertTrue(dir.mkdirs());
        }

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxCenter(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_center_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetWidth += 100;
                targetHeight += 100;
                targetImage.flush();
            }
            sourceImage.flush();
        }

        {
            final String name = "1920x1080.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxCenter(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_center_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetImage.flush();
                targetWidth += 100;
                targetHeight += 100;
            }
            sourceImage.flush();
        }
    }


    @Test(enabled = false)
    public void resizeInboxLowerRight() throws IOException {

        final File dir = new File("target/resized/LowerRight");
        if (!dir.isDirectory()) {
            assertTrue(dir.mkdirs());
        }

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            assertNotNull(sourceImage);
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxLowerRight(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_lower_right_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetImage.flush();
                targetWidth += 100;
                targetHeight += 100;
            }
            sourceImage.flush();
        }

        {
            final String name = "1920x1080.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeInboxLowerRight(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + "_lower_right_" + targetWidth + "x"
                                   + targetHeight + ".png"));
                targetImage.flush();
                targetWidth += 100;
                targetHeight += 100;
            }
            sourceImage.flush();
        }
    }


    @Test(enabled = true)
    public void resizeOutboxUpperLeft() throws IOException {

        final File dir = new File("target/resized/OutboxUpperLeft");
        assertTrue(dir.isDirectory() || dir.mkdirs());

        {
            final String name = "506x1024.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 200, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 800, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeOutboxUpperLeft(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + targetWidth + "x" + targetHeight
                                   + ".png"));
                targetWidth += 100;
                targetHeight += 100;
                targetImage.flush();
            }
            sourceImage.flush();
        }

        {
            final String name = "1920x1080.jpg";
            final BufferedImage sourceImage;
            try (InputStream sourceStream
                = ScalrTest.class.getResourceAsStream("/" + name)) {
                sourceImage = ImageIO.read(sourceStream);
            }
            int targetWidth = Math.max(sourceImage.getWidth() - 1800, 100);
            int targetHeight = Math.max(sourceImage.getHeight() - 200, 100);
            for (int i = 0; i < 5; i++) {
                final BufferedImage targetImage = Scalr.resizeOutboxUpperLeft(
                    sourceImage, Method.SPEED, targetWidth, targetHeight);
                ImageIO.write(targetImage, "png", new File(
                              dir, name + targetWidth + "x" + targetHeight
                                   + ".png"));
                targetImage.flush();
                targetWidth += 100;
                targetHeight += 100;
            }
            sourceImage.flush();
        }
    }


}

