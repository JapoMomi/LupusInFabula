// Get reference to the div elements containing the JSP includes
const friendsPage = document.getElementById('friendsPage');
const changeCredentialsPage = document.getElementById('changeCredentialsPage');

// Add event listener to the radio buttons
const radioInputs = document.querySelectorAll('.radio-inputs input[type="radio"]');
radioInputs.forEach(input => {
    input.addEventListener('change', function() {
        // Toggle display based on selected radio button
        if (this.value === 'friends') {
            friendsPage.style.display = 'block';
            changeCredentialsPage.style.display = 'none';
        } else if (this.value === 'changeCredentials') {
            friendsPage.style.display = 'none';
            changeCredentialsPage.style.display = 'block';
        }
    });
});