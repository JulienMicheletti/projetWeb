$(document).ready(function () {
    //Modifier ou Supprimer un utilisateur
    $(".formMod").submit(function (e) {
        var parent = $(this).parent();
        e.preventDefault();
        var form = $(this);
        console.log(form.serialize());
        var url = form.attr('action');
        $.ajax({
            type:"POST",
            url: url,
            data: form.serialize().concat("&mod="+document.activeElement.getAttribute('value')),
            success:function (data) {
                if (data == "Supprimer") {
                    parent.remove();
                }
            },
            error:function(message){
                $('body').prepend('<div class="red">Erreur lors de la requete à la BDD : '+message+'</div>');
            }
        })
    })
})
