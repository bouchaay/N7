import os
import hashlib
from collections import defaultdict

directory_path = "/data/train/angry"

# fonction pour hasher un fichier
def hash_file(filename):
    hasher = hashlib.sha256()
    with open(filename, 'rb') as f:
        while True:
            data = f.read(65536)  # Read in 64k chunks
            if not data:
                break
            hasher.update(data)
    return hasher.hexdigest()

# fonction pour trouver les images dupliquées
def find_duplicate_images(directory):
    duplicates = defaultdict(list)
    for root, dirs, files in os.walk(directory):
        for filename in files:
            filepath = os.path.join(root, filename)
            if os.path.isfile(filepath):
                try:
                    file_hash = hash_file(filepath)
                    duplicates[file_hash].append(filepath)
                except Exception as e:
                    print(f"Error reading {filepath}: {e}")
    return {k: v for k, v in duplicates.items() if len(v) > 1}

# fonction pour supprimer les images dupliquées
def delete_duplicate_images(directory):
    duplicate_images = find_duplicate_images(directory)
    if not duplicate_images:
        print("No duplicate images found.")
        return
    
    for hash_value, filenames in duplicate_images.items():
        print(f"Files: {filenames}")
        for filename in filenames[1:]:
            os.remove(filename)
            print(f"Deleted: {filename}")
        
# effacer les images dupliquées
def main():
    delete_duplicate_images(directory_path)

if __name__ == "__main__":
    main()
