document
  .getElementById("registrationForm")
  .addEventListener("submit", async function (e) {
    e.preventDefault(); // Prevent default form submission

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

    // Validation logic
    if (!fullName) {
      errors.fullName = "*Full Name is required.";
    }
    if (!email || !/^\S+@\S+\.\S+$/.test(email)) {
      errors.email = "*Please enter a valid email address.";
    }
    if (!phone || !/^\d{10}$/.test(phone)) {
      errors.phone = "*Please enter a valid phone number.";
    }
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
      return;
    }

    // If no errors, send data to the backend
    const formData = new FormData();
    formData.append("fullName", fullName);
    formData.append("email", email);
    formData.append("phone", phone);
    formData.append("photo", photo);

    try {
      const response = await fetch("http://localhost:8080/api/registrations", {
        method: "POST",
        body: formData,
      });

      if (response.ok) {
        const data = await response.json();
        console.log("Response from server:", data);

        // Remove the leading "./" from badgePath
        const sanitizedBadgePath = data.badgePath.replace(/^\.\//, "");

        // Construct the full URL for the badge
        const badgeUrl = `http://localhost:8080/${sanitizedBadgePath}`;

        // Redirect to thank you page with badge URL and user name
        window.location.href = `thankyou.html?badgeUrl=${encodeURIComponent(
          badgeUrl
        )}`;
      } else {
        const errorText = await response.text();
        alert("Error submitting form. Please try again.");
        console.error("Server response error:", errorText);
      }
    } catch (error) {
      alert("Error submitting form. Please try again.");
      console.error("Error:", error);
    }
  });
