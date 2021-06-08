# How to replicate our research

## 1. Download the datasets
Our dataset is a set of images we get from combining [1] and [2] and preprocessing the dataset by resizing all the images in the dataset. We resized all the images into 256 x 256 pixels by using Python Image Library (PIL). Our combined dataset contains 6 different classes (Blast, Blight, Brownspot, Hispa, Healthy, Tungro) and we have 80 images for each class.

### References:
* [1] Perreira, J. (2019, November). Rice Disease, Version 1. Retrieved May 18, 2021 from https://www.kaggle.com/jonathanrjpereira/rice-disease.
* [2] Setiady, T. (2021, May). Leaf Rice Disease, Version 1. Retrieved May 19, 2021 from https://www.kaggle.com/tedisetiady/leaf-rice-disease-indonesia.

## 2. Create a Deep Learning model using TensorFlow
We don't build the model from scratch, we use transfer learning method instead. our model is based on InceptionV3 architecture that has been trained on ImageNet. We add some layers to the InceptionV3 architecture.
After that, we train the model with the dataset we get from the first step. And the last part of this step is to export the model.

## 3. Deploy the model on the cloud server

## 4. Create an API to connect our mobile App to our cloud server

## 5. Create the mobile App for Android


