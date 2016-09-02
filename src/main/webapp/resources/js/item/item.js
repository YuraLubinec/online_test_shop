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
      success: function() {
        alert('Item was added to your cart')
      },
      error: function(jqXHR) {
        alert('Smth wrong... code: ' + jqXHR.status);
      },
    });
  };
  
  $(document).on('click', '.delete', function() {
	  if(confirm('Do you really want to delete this item?')){
		  var tr = $(this);
		  deleteItem($(this).prop('id'),tr);
		
	  }  
	  return false;
	  });

	  function deleteItem(input,tr) {
	    $.ajax({
	      type: 'DELETE',
	      url: contextPath + '/item/delete',
	      contentType: 'application/json',
	      data: JSON.stringify(input),
	      success: function(data, textStatus, jqXHR) {
		              tr.closest('tr').remove();  
	      },
	      error: function(jqXHR) {
	        alert('Smth wrong... code: ' + jqXHR.status);
	      },
	    });
	  };
});