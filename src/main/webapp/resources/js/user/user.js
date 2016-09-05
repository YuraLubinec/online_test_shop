$(function() {
  var contextPath = $('#contextPath').val();
  $(document).on('click', '.deleteFromCart', function() {
    if (confirm('Do you really want to remove this item from your cart?')) {
      deleteItem($(this));
    }
    return false;
  });

  function deleteItem(butObj) {
    $.ajax({
      type: 'DELETE',
      url: contextPath + '/item/deleteFromUserCart',
      contentType: 'application/json',
      data: JSON.stringify(butObj.prop('id')),
      success: function() {
        butObj.closest('tr').remove();
      },
      error: function(jqXHR) {
        alert('Smth wrong... code: ' + jqXHR.status);
      },
    });
  };
});