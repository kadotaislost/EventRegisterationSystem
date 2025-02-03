document.getElementById("viewBadgeBtn").addEventListener("click", function () {
  // Simulate the backend response for the sake of the example
  const backendResponse = {
    badgePath: "./badges/80_badge.pdf",
  };

  // Construct the full URL for the badge file
  const badgeUrl =
    "http://localhost:8080" + backendResponse.badgePath.substring(1);

  // Open the badge URL in a new tab
  window.open(badgeUrl, "_blank");
});
