$(document).ready(function(){

    $('.modalWindow .addBtn').on('click', function(event){
        $('.myForm #modal').modal;
    });

    $('.tab-div .delBtn').on('click', function(event){
        $('.myForm #delete-modal').modal;
    });

    /*$('.tab-div .eBtn').on('click', function(event){
         event.preventDefault();

         var href = $(this).attr('href');

         $.get(href, function(country, status){

                var obj = JSON.parse(country);

               $('.editForm #edit-name').val(obj.name);
               $('.editForm #edit-capital').val(obj.capital);

               $('.editForm #edit-modal').modal;
         });

         country = null;
    });*/
});