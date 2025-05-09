// components/layout/Footer.jsx
import React from 'react';
import './Footer.css';

const Footer = () => {
    return (
        <footer className="footer">
            <div className="container text-center">
                <p>&copy; {new Date().getFullYear()} Système d'Évaluation de Stage</p>
            </div>
        </footer>
    );
};

export default Footer;
