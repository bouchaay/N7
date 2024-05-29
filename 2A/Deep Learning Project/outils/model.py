from keras.applications import VGG19
from keras.models import Model
from keras.layers import Dense, GlobalAveragePooling2D
import tensorflow
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras import optimizers
from tensorflow.keras.layers import Conv2D, MaxPooling2D
from tensorflow.keras.layers import Dense, Flatten



def create_model_simple(image_size = 64, num_classes=6):
    
    model = Sequential()
    
    model.add(Conv2D(32, (3, 3), activation='relu', input_shape=(image_size, image_size, 3)))
    model.add(MaxPooling2D((2, 2)))
    
    model.add(Conv2D(64, (3, 3), activation='relu'))
    model.add(MaxPooling2D((2, 2)))
    
    model.add(Conv2D(96, (3, 3), activation='relu'))
    model.add(MaxPooling2D((2, 2)))
    
    model.add(Conv2D(128, (3, 3), activation='relu'))
    model.add(MaxPooling2D((2, 2)))
    
    model.add(Flatten())
   
    model.add(Dense(512, activation='relu')) 
    model.add(Dense(num_classes, activation="softmax"))  # 6 classes d'émotions différentes
    
   
    
    return model
    

def create_model_vgg19(image_size = 64):
    
    # Charger le modèle VGG19 pré-entraîné avec les poids du jeu de données ImageNet
    base_model = VGG19(weights='imagenet', include_top=False, input_shape=(image_size, image_size, 3)) 

    x = base_model.output
    x = GlobalAveragePooling2D()(x)

    x = Dense(128, activation='relu')(x) 
    predictions = Dense(6, activation='softmax')(x) 

    model = Model(inputs=base_model.input, outputs=predictions)

    for layer in base_model.layers:
        layer.trainable = False

    model.compile(optimizer='adam', loss='categorical_crossentropy', metrics=['accuracy'])

    return model
    


# Création du modèle simple

#model = create_model_simple()

# Affichage de la structure du modèle
#model.summary()
