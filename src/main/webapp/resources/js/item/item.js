$(function() {

  $(document).on('click', '.addToCart', function() {

    addItemsToUserCart($(this).prop('id'));

    return false;
  });

  function addItemsToUserCart(input) {
    $.ajax({
      type: 'POST',
      url: '/testShop/item/addToUserCart',
      contentType: 'application/json',
      data: JSON.stringify(input),
      success: function(data, textStatus, jqXHR) {
        alert('Item was added to your cart')
      },
      error: function(jqXHR) {
        alert('Smth wrong... code: ' + jqXHR.status + contextPath + JSON.stringify(input));
      },
    });
  };

});