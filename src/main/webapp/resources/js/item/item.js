$(function() {
  var contextPath = $('#contextPath').val();
  $(document).on('click', '.addToCart', function() {
    addItemsToUserCart($(this).prop('id'));
    return false;
  });

  function addItemsToUserCart(input) {
    $.ajax({
      type: 'POST',
      url: contextPath + '/item/addToUserCart',
      contentType: 'application/json',
      data: JSON.stringify(input),
      success: function(data) {
        alert(data)
      },
      error: function(jqXHR) {
        alert('Smth wrong... code: ' + jqXHR.status);
      },
    });
  };
  $(document).on('click', '.delete', function() {
    if (confirm('Do you really want to delete this item?')) {
      deleteItem($(this));
    }
    return false;
  });

  function deleteItem(butObj) {
    $.ajax({
      type: 'DELETE',
      url: contextPath + '/admin/item/delete',
      contentType: 'application/json',
      data: JSON.stringify(butObj.prop('id')),
      success: function() {
        butObj.closest('div.item').remove();
      },
      error: function(jqXHR) {
        alert('Smth wrong... code: ' + jqXHR.status);
      },
    });
  };
	
  $(document).on('click', '.back-btn', function() {
	  window.history.back();
  });
  
});