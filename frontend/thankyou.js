// Get query parameters from URL
const urlParams = new URLSearchParams(window.location.search);
let badgeUrl = urlParams.get("badgeUrl");

// Log the badgeUrl to check its value
console.log("Badge URL:", badgeUrl);

// Remove trailing dot if present
if (badgeUrl && badgeUrl.endsWith(".")) {
  badgeUrl = badgeUrl.slice(0, -1);
  console.log("Sanitized Badge URL:", badgeUrl);
}

// Validate the badgeUrl
function isValidUrl(string) {
  try {
    new URL(string);
    return true;
  } catch (_) {
    return false;
  }
}

// When the "View Badge" button is clicked, open the badge PDF in a new tab
document.getElementById("viewBadge").addEventListener("click", function () {
  if (badgeUrl && isValidUrl(badgeUrl)) {
    window.open(badgeUrl, "_blank");
  } else {
    alert("Badge URL is not available or invalid.");
  }
});
