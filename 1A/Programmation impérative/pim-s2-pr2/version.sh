base=files
spec=$base.ads
body=$base.adb

erreur()
    # Afficher un message d'erreur et s'arrêter
{
    echo $*
    exit 2
}

verifier_lien()
    #1 Vérifier que le nom $1 est bien un lien symbolique
{
    nom="$1"
    if [ -z "$nom" ] ; then
	erreur "Erreur pas de nom de fichier (verifier_lien)"
    fi
    if [ ! -L "$nom" ] ; then	# Ce n'est pas un lien symbolique
	if [ -f "$nom" ]  ; then	# mais un fichier normal
	    erreur "Erreur $nom n'est pas un lien symbolique. Abandon."
	fi
    fi
}

changer_lien()
    # Vérifier que le fichier $1 est bien un lien symbolique
    # $1 nom_lien
    # $3 fichier source
    
{
    nom_lien="$1"
    verifier_lien "$nom_lien"

    fichier="$2"
    [ -f $nom_lien ] && rm "$nom_lien"
    ln -s "$fichier" "$nom_lien"
}

version=$1
if [ -z "$version" ] ; then
    echo "Il manque la version à utiliser."
    echo "Usage : $0 [v1 | v2]"
    echo "Exemple : $0 v1"
    exit 1
fi;
for ext in ads adb ; do
    changer_lien $base.$ext $base-$version.$ext
done;

# Forcer la recompilation.
gnatclean *.adb
