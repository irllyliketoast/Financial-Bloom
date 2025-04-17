// Converted from SignUp.html to React JSX format
// All comments, structure, and original JavaScript are preserved and adapted

import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './Styles/SignUp.css';
import logo from './Graphics/logo.png';
import faceBook from './Graphics/facebook.png';
import insta from './Graphics/insta.png';
import xIcon from './Graphics/x.png';

function SignUp() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [alertMessage, setAlertMessage] = useState('');
    const [showAlert, setShowAlert] = useState(false);
    const navigate = useNavigate();

    // JavaScript validation and UI control
    function validateSignUp(event) {
        event.preventDefault();
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\W)(?!.*\s).{6,}$/;

        if (!passwordRegex.test(password)) {
            showAlertSignUp("Password must be at least 6 characters long, contain an uppercase letter, a lowercase letter, a special character, and cannot contain spaces.");
            setPassword('');
            setConfirmPassword('');
            return;
        }
        if (password !== confirmPassword) {
            showAlertSignUp("Passwords do not match.");
            setPassword('');
            setConfirmPassword('');
            return;
        }

        // MAKE A FETCH CALL TO THE BACKEND API
        fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                fName: firstName,
                lName: lastName,
                email: email,
                password: password
            })
        })
            .then(res => res.text()) // backend returns plain text
            .then(data => {
                showAlertSignUp(data); // display backend response in alert
                if (data.includes("success")) {
                    // Redirect to log in after successful signup
                    setTimeout(() => navigate("/login"), 1000);
                }
            })
            .catch(err => {
                console.error(err);
                showAlertSignUp("Server error. Please try again.");
            });
    }

    function showAlertSignUp(message) {
        setAlertMessage(message);
        setShowAlert(true);
    }

    function closeAlertSignUp() {
        setShowAlert(false);
    }

    return (
        <>
            {/*
        Base HTML structure by Chris Lane.
        Styling, user interface design, animations, and JavaScript functionality
        implemented and customized by Laura Estremera.
            */}

            {/* Top header bar */}
            <header className="header">
                <img src={logo} alt="Financial Bloom logo"/>
                Financial Bloom
            </header>

            {/* Page wrapper: holds form + information panels */}
            <div className="wrapper">
                {/* Left-side sign-up form */}
                <div className="sign-up-form">
                    <h1>Create an Account</h1>

                    {/* Form with event-based validation */}
                    <form onSubmit={validateSignUp}>
                        <input
                            type="text"
                            id="firstName"
                            placeholder="First Name"
                            required
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                        /><br/>

                        <input
                            type="text"
                            id="lastName"
                            placeholder="Last Name"
                            required
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                        /><br/>

                        <input
                            type="email"
                            id="email"
                            placeholder="Email"
                            required
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        /><br/>

                        <input
                            type="text"
                            id="username"
                            placeholder="Username"
                            required
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        /><br/>

                        <input
                            type="password"
                            id="password"
                            placeholder="Password"
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        /><br/>

                        <input
                            type="password"
                            id="confirmPassword"
                            placeholder="Confirm Password"
                            required
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        /><br/>

                        <input type="submit" value="Sign Up"/>
                    </form>
                </div>


                    {/* Custom alert box overlay */}
                    {showAlert && (
                        <div id="customAlertOverlaySignUp" className="alert-overlay">
                            <div id="customAlertSignUp" className="alert-box">
                                <p id="alertMessageSignUp">{alertMessage}</p>
                                <button onClick={closeAlertSignUp}>OK</button>
                            </div>
                        </div>
                    )}

                    {/* Right-side informational content */}
                    <div className="info-content">
                        <h3>Welcome to Financial Bloom!</h3>
                        <p>Discover a fun and engaging way to take control of your finances through your very own
                            virtual garden. At Financial Bloom, we guide you on a journey to financial responsibility,
                            where every smart financial decision helps your garden grow.</p>

                        <h3>What Makes Us Different?</h3>
                        <p>At Financial Bloom, we believe that managing your money should be more than just a chore – it
                            should be an enjoyable and rewarding experience. By combining financial education with a
                            gamified approach, we turn budgeting, saving, and paying off debt into a game that motivates
                            you to make smart financial choices.</p>
                    <p>As you improve your financial health, watch your virtual garden flourish. Every positive financial decision contributes to the growth of beautiful plants, making your journey to financial wellness not only effective but visually rewarding.</p>

                    <h3>How We Help You</h3>
                    <p>Our easy-to-use app helps you track your income, expenses, debt, and savings while allowing you to set personalized budgets. With tailored suggestions and a dynamic garden that reflects your financial habits, you’ll receive motivation every step of the way.</p>

                    <p>Key Features Include:</p>
                    <ul>
                        <li><strong>Track Your Spending:</strong> Get insights on where your money is going and how to improve.</li>
                        <li><strong>Set Financial Goals:</strong> Create custom budgets, savings plans, and milestones for debt reduction.</li>
                        <li><strong>Visual Garden Growth:</strong> Watch your virtual garden thrive as you meet your financial targets.</li>
                    </ul>

                    <h3>Start Your Journey Today</h3>
                    <p>Sign up now to begin cultivating your financial future with the help of Financial Bloom. Whether you're just starting your financial journey or looking to refine your habits, we're here to help you grow and thrive.</p>

                    <p>
                        Ready to get started? Create an account or{' '}
                        <Link to="/login">log in</Link> to access your personalized dashboard!
                    </p>


                    <p><strong>Still have questions?</strong> Check out our <a href="/tutorial">interactive tutorial</a>
                        to guide you through everything you need to know about the app’s features and how to make the most of your experience.</p>
                </div>

                {/* Social media icons section */}
                <section className="info-content">
                    <p className="follow-us">Follow us on:</p>
                    <section className="social-media">
                        <img src={faceBook} alt="Facebook"/>
                        <img src={insta} alt="Instagram"/>
                        <img src={xIcon} alt="X"/>
                    </section>
                </section>
            </div>

            {/* Page footer */}
            <footer>2025 Financial Bloom &copy; All rights reserved.</footer>
        </>
    );
}

export default SignUp;
