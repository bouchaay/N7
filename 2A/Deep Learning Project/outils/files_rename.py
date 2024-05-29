import os

# Fonction pour renommer les fichiers d'images dans un répertoire
def rename_images(directory_path, keyword):
    # Vérifier si le chemin est un dossier
    if not os.path.isdir(directory_path):
        print("Le chemin spécifié n'est pas un dossier valide.")
        return

    # Liste des extensions d'images valides
    valid_extensions = ['.jpg', '.jpeg', '.png', '.gif']

    # Initialiser un compteur pour incrémenter le numéro
    i = 1

    # Parcourir tous les fichiers du répertoire
    for filename in os.listdir(directory_path):
        # Récupérer le chemin complet du fichier
        filepath = os.path.join(directory_path, filename)

        # Vérifier si le fichier est un fichier image avec une extension valide
        if os.path.isfile(filepath) and os.path.splitext(filename)[1].lower() in valid_extensions:
            # Construire le nouveau nom de fichier avec le mot-clé et l'incrément
            new_filename = f"{keyword}{i}{os.path.splitext(filename)[1]}"
            
            # Renommer le fichier
            os.rename(filepath, os.path.join(directory_path, new_filename))

            # Incrémenter le compteur
            i += 1

# Exemple d'utilisation de la fonction pour renommer les fichiers d'images
emotion = "sad"
directory_path = "image_project/" + emotion
rename_images(directory_path, "intermediate_" + emotion + "_")
rename_images(directory_path, emotion)