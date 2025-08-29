/* Password Validation */

function validatePassword(event) {

  const password = document.getElementById('password').value;
  const passwordConfirm = document.getElementById('password_confirm').value;
    // Note: Assuming sources have 'password' and 'password_confirm' as ids

  if (password != passwordConfirm) {
    $("#alert-password").dialog("open");
      // alert('Password and Password (Confirm) do not match. Please amend then click button again.');
      // Note: Switched to jQuery widget
     event.preventDefault();
    return false;
  }

  return true;

}


/* All Checkboxes Selection */

function selectAll(sourceId, targetsName) {

  const checkbox   = document.getElementById(sourceId);
  const checkboxes = document.getElementsByName(targetsName);

  for (var counter = 0, limit = checkboxes.length; counter < limit; counter++) {
    checkboxes[counter].checked = checkbox.checked;
  }

}


/* Removal of Facebook's hash in URL */

if (window.location.hash == '#_=_') {
  history.replaceState
    ? history.replaceState(
        null,
        null,
        window.location.href.split('#')[0])
    : window.location.hash = '';
}
