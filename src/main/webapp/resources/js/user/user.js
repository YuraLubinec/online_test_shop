$(function() {
  var contextPath = $('#contextPath').val();
  $(document).on('click', '.deleteFromCart', function() {
    deleteItem($(this));
    return false;
  });

  function deleteItem(butObj) {
    $.ajax({
      type: 'DELETE',
      url: contextPath + '/item/deleteFromUserCart',
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