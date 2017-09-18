$(function(){
    $('.img').click(function () {
        $('.img .duigou').remove()
        $(this).append('<img class="duigou" src="img/right.png" alt="" th:src="@{/templates/app/img/right.png}">');
    })
})