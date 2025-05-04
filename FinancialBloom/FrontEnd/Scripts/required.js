function getCookie(name) {
    const cookieString = document.cookie;
    const cookies = cookieString.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            return cookie.substring(name.length + 1);
        }
    }
    return null;
}

function deleteCookie() {
    document.cookie = "userId=; Path=/; Expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    console.log("Cookie successfully deleted");
}

function verifyUser() {
    const cookie = getCookie("userId");
    if (!cookie) {
        // Use replace() instead of href to prevent back-button access
        window.location.replace("Login.html");
    }
    return cookie;
}

function logout() {
    deleteCookie();
    // Clear sessionStorage and localStorage if used
    sessionStorage.clear();
    localStorage.clear();
    // Force a non-cached redirect
    window.location.replace("Login.html");
}

// 🖱️ Custom Cursor Follower Script
const cursor = document.getElementById('customCursor');
if (cursor) {
    document.addEventListener('mousemove', (e) => {
        cursor.style.left = `${e.clientX}px`;
        cursor.style.top = `${e.clientY}px`;
    });
}

// Add event listener for logout links
document.querySelectorAll('a.logout').forEach(link => {
    link.addEventListener('click', (e) => {
        e.preventDefault();
        logout();
    });
});

// check for back/forward navigation
function checkNavigationType() {
    // Check if page was loaded from cache (back/forward button)
    if (window.performance && window.performance.navigation.type === 2) {
        if (!getCookie("userId")) {
            window.location.replace("Login.html");
        }
    }

    // Additional check for pageshow event
    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type === 2)) {
            if (!getCookie("userId")) {
                window.location.replace("Login.html");
            }
        }
    });
}


checkNavigationType();
