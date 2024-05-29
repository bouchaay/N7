from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time
import os
import requests
import ssl

# Fonction pour télécharger les images à partir de Google Images
def download_images(search_term, num_images, output_folder):
    # Configuration de Selenium
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    
    url = f"https://www.google.com/search?q={search_term}&tbm=isch"
    driver.get(url)
    
    # Faire défiler la page pour charger plus d'images
    last_height = driver.execute_script("return document.body.scrollHeight")
    while True:
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        time.sleep(5)  # Attendre quelques secondes pour charger les nouvelles images
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            break
        last_height = new_height
    
    # Extraire les URLs des images
    image_tags = driver.find_elements(By.TAG_NAME, 'img')
    image_urls = [img.get_attribute('src') for img in image_tags]
    
    # Télécharger les images
    for i, img_url in enumerate(image_urls[:num_images]):
        if img_url and img_url.startswith('http'):
            img_data = requests.get(img_url).content
            with open(os.path.join(output_folder, f"{search_term}_{i}.jpg"), 'wb') as f:
                f.write(img_data)
    
    # Fermer le navigateur
    driver.quit()

# Termes de recherche et nombre d'images à télécharger
#search_terms = ["personne qui pleure", "face pleurent"]
#search_terms = ["personne en colère", "face en colère","angry"]
#search_terms = ["deguisted","dégouté","humain dégouté"]
search_terms = ["fear face","face peur","peur"]

num_images_per_term = 1000
output_directory = "image"

# Télécharger les images pour chaque terme de recherche
for term in search_terms:
    download_images(term, num_images_per_term, output_directory)