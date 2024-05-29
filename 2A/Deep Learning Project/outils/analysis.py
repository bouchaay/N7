import matplotlib.pyplot as plt

# Fonction pour afficher les courbes d'entraînement et de validation
def plot_training_analysis(history):
  
  # Récupérer les données d'entraînement et de validation
  acc = history.history['accuracy']
  val_acc = history.history['val_accuracy']
  loss = history.history['loss']
  val_loss = history.history['val_loss']
  
  # Récupérer le nombre d'époques
  epochs = range(len(acc))

  # Afficher les courbes d'entraînement et de validation pour l'accuracy
  plt.plot(epochs, acc, 'b', linestyle="--",label='Training acc')
  plt.plot(epochs, val_acc, 'g', label='Validation acc')
  plt.title('Training and validation accuracy')
  plt.legend()
  plt.figure()

  # Afficher les courbes d'entraînement et de validation pour la loss
  plt.plot(epochs, loss, 'b', linestyle="--",label='Training loss')
  plt.plot(epochs, val_loss,'g', label='Validation loss')
  plt.title('Training and validation loss')
  plt.legend()

  # Afficher les graphiques
  plt.show()