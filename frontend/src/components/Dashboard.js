import React, { useEffect, useState } from 'react';

function Dashboard() {
    const [tuteurs, setTuteurs] = useState([]);

    useEffect(() => {
        const fetchTuteurs = async () => {
            try {
                const response = await fetch('/tuteurs');
                const data = await response.json();
                setTuteurs(data);
            } catch (error) {
                console.error('Erreur:', error);
            }
        };
        fetchTuteurs();
    }, []);

    return (
        <div>
            <h1>Dashboard</h1>
            <table>
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Entreprise</th>
                </tr>
                </thead>
                <tbody>
                {tuteurs.map((tuteur) => (
                    <tr key={tuteur.id}>
                        <td>{tuteur.nom}</td>
                        <td>{tuteur.prenom}</td>
                        <td>{tuteur.email}</td>
                        <td>{tuteur.entreprise}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Dashboard;