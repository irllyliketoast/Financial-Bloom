function getCookie(name) { // returns userid when you do 'getCookie("userId")'
    const cookieString = document.cookie;
    const cookies = cookieString.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();
        if (cookie.startsWith(name + '=')) {
            return cookie.substring(name.length + 1);
        }
    }
    return null;
};

function deleteCookie(){
    document.cookie = "userId=; expires=Thu, 01 Jan 1970 00:00:00 UTC;";
    console.log("Cookie sucessfully deleted");
};

function verifyUser() {
    const cookie = getCookie("userId"); // when setting cookie, do this
    if (!cookie) {
        window.location.replace("Login.html");
    }
    return cookie;
};

// 🖱️ Custom Cursor Follower Script
const cursor = document.getElementById('customCursor');
document.addEventListener('mousemove', (e) => {
    cursor.style.left = `${e.clientX}px`;
    cursor.style.top = `${e.clientY}px`;
});


const logout = document.querySelectorAll('a.logout')[0];
logout.addEventListener('click', (e)=> {
    e.preventDefault();
    deleteCookie();
    window.location.href = logout.href;
});