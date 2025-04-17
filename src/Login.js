import React, { useState } from 'react';
import './Styles/Login.css';
import logo from './Graphics/logo.png';
import faceBook from './Graphics/facebook.png';
import insta from './Graphics/insta.png';
import xIcon from './Graphics/x.png';
import { Link, useNavigate } from 'react-router-dom';

// Add these fonts in index.html OR via import in CSS if needed
// import '@fontsource/silkscreen';
// import '@fontsource/play'; // alternative if you want to use npm font packages

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [alertMessage, setAlertMessage] = useState('');
    const [showAlert, setShowAlert] = useState(false);
    const navigate = useNavigate();

    const validateForm = (event) => {
        event.preventDefault();

        const usernameRegex = /^\S+$/;
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.*\s).{6,}$/;

        if (!username && !password) return showAlertBox("Please enter a username and password.");
        if (!username) return showAlertBox("Please enter a username.");
        if (!password) return showAlertBox("Please enter a password.");
        if (!usernameRegex.test(username)) return showAlertBox("Username cannot contain spaces.");
        if (!passwordRegex.test(password)) {
            return showAlertBox("Password must be at least 6 characters long, contain an uppercase letter, a lowercase letter, a special character, and cannot contain spaces.");
        }

        // Send to backend
        fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
            .then(res => res.text())
            .then(data => {
                console.log("Server response:", data);
                if (data.includes("success")) {
                    navigate('/dashboard'); // ✅ adjust to your target page
                } else {
                    showAlertBox(data);
                }
            })
            .catch(() => showAlertBox("Server error. Please try again."));
    };

    const showAlertBox = (message) => {
        setAlertMessage(message);
        setShowAlert(true);
    };

    const closeAlert = () => {
        setShowAlert(false);
    };

    return (
        <>
            {/* External Fonts via Google Fonts (make sure this is included in your index.html) */}
            {/* OR move these to public/index.html <head>:
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Silkscreen&display=swap">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Play:wght@400;700&display=swap">
      */}

            {/* Fixed header with logo and title */}
            <header className="header">
                <img src={logo} alt="Financial Bloom logo" />
                <h1 className="logo-title">Financial Bloom</h1>
            </header>

            {/* Custom Alert Overlay */}
            {showAlert && (
                <div id="customAlertOverlay" className="alert-overlay">
                    <div id="customAlert" className="alert-box">
                        <p id="alertMessage">{alertMessage}</p>
                        <button onClick={closeAlert}>OK</button>
                    </div>
                </div>
            )}

            {/* Wrapper holds main login form and side panel */}
            <div className="wrapper side-panel-open">
                <div className="main">

                    {/* Login form */}
                    <form onSubmit={validateForm}>
                        <h1>Welcome to Financial Bloom!</h1>
                        <fieldset id="first">
                            <input
                                type="text"
                                name="username"
                                id="username"
                                placeholder="Username"
                                autoFocus
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                            /><br />
                            <input
                                type="password"
                                name="password"
                                id="password"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            /><br />
                            <input type="submit" value="Log In" id="LogIn" />
                            <button type="button" id="reset" onClick={() => showAlertBox("Reset feature coming soon!")}>
                                Forgot username or password?
                            </button>
                        </fieldset>
                    </form>

                    {/* Social media section */}
                    <section className="social-media">
                        Follow us on:<br />
                        <img src={faceBook} alt="Facebook" />
                        <img src={insta} alt="Instagram" />
                        <img src={xIcon} alt="X" />
                    </section>

                    {/* Side panel with intro and sign-up CTA */}
                    <div className="side-panel">
                        <h2>Welcome to Financial Bloom!</h2>
                        <p>Discover a fun and engaging way to take control of your finances through your very own virtual garden.
                            At Financial Bloom, we guide you on a journey to financial responsibility, where every smart financial decision
                            helps your garden grow.</p>
                        <p>Sign in to get started, set your goals, and cultivate a brighter financial future!</p>
                        <p>Don't have an account?</p>
                        <Link to="/signup" id="SignUp">Sign Up</Link>
                    </div>
                </div>
            </div>

            {/* Optional footer garden feature can be re-enabled here */}
            {/* <div className="footer-garden">
        <img src={plantGif} className="plant" />
      </div> */}

            {/* Footer */}
            <footer>2025 Financial Bloom &copy; All rights reserved.</footer>
        </>
    );
}

export default Login;
