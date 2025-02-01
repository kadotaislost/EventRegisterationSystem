document
  .getElementById("registrationForm")
  .addEventListener("submit", function (e) {
    e.preventDefault(); // Prevent form submission

    // Clear previous error messages
    const errorMessages = document.querySelectorAll(".error-message");
    errorMessages.forEach(function (errorMessage) {
      errorMessage.innerHTML = "";
    });

    // Get form data
    const fullName = document.getElementById("fullName").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const photo = document.getElementById("photo").files[0];

    let errors = {};

    // Full Name Validation
    if (!fullName) {
      errors.fullName = "*Full Name is required.";
    }

    // Email Validation
    if (!email || !/^\S+@\S+\.\S+$/.test(email)) {
      errors.email = "*Please enter a valid email address.";
    }

    // Phone Number Validation (Must be exactly 10 digits)
    if (!phone || !/^\d{10}$/.test(phone)) {
      errors.phone = "*Please enter a valid phone number.";
    }

    // Photo Validation (Must be JPG or PNG)
    if (!photo) {
      errors.photo = "*Please upload a photo.";
    } else if (!["image/jpeg", "image/png"].includes(photo.type)) {
      errors.photo = "*Photo must be in JPG or PNG format.";
    }

    // Display errors below the respective inputs
    if (Object.keys(errors).length > 0) {
      for (const field in errors) {
        document.getElementById(`${field}Error`).innerHTML = errors[field];
      }
    } else {
      // If no errors, you can proceed with form submission (e.g., via AJAX or form action)
      alert("Form is valid! You can proceed with form submission.");
      // Optionally submit the form here
      // e.target.submit();  // Uncomment this to actually submit the form
    }
  });
