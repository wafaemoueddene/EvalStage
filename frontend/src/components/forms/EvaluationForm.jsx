import React, { useState } from "react";
import axios from "axios";


export default function EvaluationForm() {
    const [competenceIndividu, setCompetenceIndividu] = useState({
        "Etre capable d'analyse et de synthèse ": "",
        "Etre capable de proposer des méthodes et des axes de travail": "",
        "Etre capable de faire adhérer les acteurs ": "",
        "Etre capable de s'autoévaluer": "",
        "Etre capable d'identifier des problèmes complexes ": ""
    });
    const [competenceEntreprise, setCompetenceEntreprise] = useState({
        "Etre capable d'analyser le fonctionnement de l'entreprise d'acceuil ": "",
        "Etre capable d'analyser la démarche projet , et d'organiser et de structurer un projet ": "",
        "Etre capable d'apprendre à déceler et à comprendre la politique environnementale de l'entreprise ": "",
        "Etre capable de rechercher , de sélectionner l'information  nécessaire à ses activités ": ""
    });
    const [competencesScientifiques, setCompetencesScientifiques] = useState({
        "Etre capable d'assurer la conception  préliminaire de produits /services/ processus /usages ": "",
    });



    const [avisGeneral, setAvisGeneral] = useState("");
    const [activeTab, setActiveTab] = useState(1);
    const [stagiaire, setStagiaire] = useState("");
    const [emailTuteur, setEmailTuteur] = useState("");
    const [emailStagiaire, setEmailStagiaire] = useState("");
    const [entreprise, setEntreprise] = useState("");
    const [tuteur, setTuteur] = useState("");
    const [dateDebut, setDateDebut] = useState("");
    const [dateFin, setDateFin] = useState("");
    const [themeProjet, setThemeProjet] = useState("");
    const [objectifs, setObjectifs] = useState("");
    const [commentaire, setCommentaire] = useState("");
    const [error, setError] = useState("");
    const [errorProjet, setErrorProjet] = useState("");
    const [noteIndividu, setNoteIndividu] = useState("");
    const [noteEntreprise, setNoteEntreprise] = useState("");
    const [noteScientifique, setNoteScientifique] = useState("");
    const [noteMetier, setNoteMetier] = useState("");
    const [noteStage, setNoteStage] = useState("");
    const [applicationValue, setApplicationValue] = useState("4");
    const [ouvertureValue, setOuvertureValue] = useState("");
    const [qualiteValue, setQualiteValue] = useState("");
    const [competenceMetier, setCompetenceMetier] = useState({});
    const [nouvelleCompetence, setNouvelleCompetence] = useState("");
    const [nouveauNiveau, setNouveauNiveau] = useState("");

    // Fonction pour ajouter une compétence
    const ajouterCompetence = () => {
        if (nouvelleCompetence.trim() !== '') {
            setCompetenceMetier({
                ...competenceMetier,
                [nouvelleCompetence]: nouveauNiveau
            });
            setNouvelleCompetence('');
            setNouveauNiveau('');
        }
    };

    // Validation des sections
    const validateAppreciations = () => {
        return applicationValue && ouvertureValue && qualiteValue;
    };

    const validateCompetences = () => {
        const individualValid = Object.values(competenceIndividu).some(value => value !== "");
        const enterpriseValid = Object.values(competenceEntreprise).some(value => value !== "");
        const scientificValid = Object.values(competencesScientifiques).some(value => value !== "");
        const metierValid = Object.values(competenceMetier).some(value => value !== "");
        return individualValid && enterpriseValid && scientificValid && metierValid;
    };

    // Fonction pour avancer entre les onglets
    const handleNextTab = () => {
        if (activeTab === 1) {
            if (!stagiaire || !emailStagiaire || !emailTuteur || !entreprise || !tuteur || !dateDebut || !dateFin) {
                setError("Veuillez remplir tous les champs avant de continuer.");
                return;
            }
            setError("");
        }

        if (activeTab === 2) {
            if (!themeProjet || !objectifs) {
                setErrorProjet("Veuillez remplir tous les champs du projet avant de continuer.");
                return;
            }
            setErrorProjet("");
        }

        if (activeTab === 3) {
            if (!validateAppreciations()) {
                alert("Veuillez remplir toutes les appréciations.");
                return;
            }
        }

        if (activeTab === 4) {
            if (!validateCompetences()) {
                alert("Veuillez remplir au moins une compétence dans chaque catégorie.");
                return;
            }
        }

        setActiveTab(prevTab => prevTab + 1);
    };


    const tabTransition = "transition-opacity duration-500";
    const handleSubmit = async () => {
        const evaluationData = {
            stagiaire,
            emailStagiaire,
            emailTuteur,
            entreprise,
            tuteur,
            dateDebut,
            dateFin,
            themeProjet,
            objectifs,
            application: applicationValue,
            ouverture: ouvertureValue,
            qualite: qualiteValue,
            competenceIndividu,
            competenceEntreprise,
            competencesScientifiques,
            competenceMetier,
            noteIndividu,
            noteEntreprise,
            noteScientifique,
            noteMetier,
            noteStage,
            commentaire,
            avisGeneral
        };
        console.log("Données du formulaire :", evaluationData);
        try {
            const response = await axios.post("http://localhost:9091/evaluations", evaluationData);
            alert("Évaluation enregistrée avec succès !");
            // console.log("Réponse du serveur :", response.data);
        } catch (error) {
            // console.error("Erreur lors de la soumission :", error);
            alert("Erreur lors de l'enregistrement de l'évaluation.");
        }
    };


    return (
        <div className="max-w-7xl mx-auto p-6 bg-gradient-to-b from-blue-50 to-white rounded-xl shadow-lg">
            {/* En-tête amélioré avec effet visuel */}
            <div className="bg-gradient-to-r from-indigo-600 via-blue-600 to-blue-700 rounded-lg p-6 mb-8 text-center shadow-lg relative overflow-hidden">
                <div className="absolute top-0 left-0 w-full h-full bg-white opacity-10">
                    <div className="absolute inset-0 bg-grid-pattern"></div>
                </div>
                <div className="relative z-10">
                    <h1 className="text-3xl font-bold text-white mb-2 tracking-wide">ÉVALUATION DE STAGE</h1>
                    <p className="text-blue-100 font-medium text-lg">Formulaire d'appréciation du tuteur</p>
                </div>
            </div>

            {/* Navigation par tabs */}
            <div className="mb-6">
                <div className="flex flex-wrap justify-center space-x-1 sm:space-x-2 border-b border-gray-200">
                    {[
                        { id: 1, label: "Informations", icon: "user" },
                        { id: 2, label: "Projet", icon: "document" },
                        { id: 3, label: "Appréciations", icon: "star" },
                        { id: 4, label: "Compétences", icon: "shield" }
                    ].map((tab) => (
                        <button
                            key={tab.id}
                            onClick={() => setActiveTab(tab.id)}
                            className={`flex items-center px-4 py-3 text-sm font-medium rounded-t-lg transition-colors duration-300 ${
                                activeTab === tab.id
                                    ? "bg-white text-blue-600 border-t border-l border-r border-gray-200"
                                    : "text-gray-500 hover:text-blue-500 hover:bg-blue-50"
                            }`}
                        >
                            {tab.label}
                        </button>
                    ))}
                </div>
            </div>

            {/* Section Informations */}
            <div className={`${activeTab === 1 ? "block" : "hidden"} ${tabTransition}`}>
                <div className="bg-white rounded-lg p-6 mb-6 shadow-md border border-blue-100 hover:shadow-lg transition-shadow duration-300">
                    <h3 className="text-lg font-bold mb-5 text-blue-700 flex items-center">
                        INFORMATIONS GÉNÉRALES
                    </h3>

                    {error && <p className="text-red-500 mb-4">{error}</p>}

                    <div className="grid grid-cols-1 md:grid-cols-2 gap-5">
                        <div className="space-y-2 group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Stagiaire (Nom et Prénom)
                            </label>
                            <input
                                type="text"
                                value={stagiaire}
                                onChange={(e) => setStagiaire(e.target.value)}
                                className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                placeholder="Jean Dupont"
                            />
                        </div>
                        <div className="space-y-2 group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Email du staigiaire
                            </label>
                            <input
                                type="email"
                                value={emailStagiaire}
                                onChange={(e) => setEmailStagiaire(e.target.value)}
                                className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                placeholder="staigiaire@gmail.com"
                            />
                        </div>

                        <div className="space-y-2 group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Tuteur (Nom et Prénom)
                            </label>
                            <input
                                type="text"
                                value={tuteur}
                                onChange={(e) => setTuteur(e.target.value)}
                                className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                placeholder="Marie Martin"
                            />
                        </div>
                        <div className="space-y-2 group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Email du tuteur
                            </label>
                            <input
                                type="email"
                                value={emailTuteur}
                                onChange={(e) => setEmailTuteur(e.target.value)}
                                className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                placeholder="tuteur@gmail.com"
                            />
                        </div>

                        <div className="space-y-2 group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Entreprise
                            </label>
                            <input
                                type="text"
                                value={entreprise}
                                onChange={(e) => setEntreprise(e.target.value)}
                                className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                placeholder="Entreprise XYZ"
                            />
                        </div>

                        <div className="space-y-2">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200">
                                Période du stage
                            </label>
                            <div className="flex items-center space-x-3">
                                <input
                                    type="date"
                                    value={dateDebut}
                                    onChange={(e) => setDateDebut(e.target.value)}
                                    className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                />
                                <span className="text-gray-400 font-medium">à</span>
                                <input
                                    type="date"
                                    value={dateFin}
                                    onChange={(e) => setDateFin(e.target.value)}
                                    className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="mt-6 flex justify-end">
                        <button
                            onClick={handleNextTab}
                            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
                        >
                            Suivant
                        </button>
                    </div>
                </div>
            </div>

            {/* Section Projet */}
            <div className={`${activeTab === 2 ? "block" : "hidden"} ${tabTransition}`}>
                <div className="bg-white rounded-lg p-6 mb-6 shadow-md border border-blue-100 hover:shadow-lg transition-shadow duration-300">
                    <h3 className="text-lg font-bold mb-5 text-blue-700 flex items-center">
                        PROJET ET OBJECTIFS
                    </h3>

                    {errorProjet && <p className="text-red-500 mb-4">{errorProjet}</p>}

                    <div className="mb-6 group">
                        <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200 mb-2">
                            Thème du projet principal
                        </label>
                        <textarea
                            className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                            rows="3"
                            placeholder="Décrivez le projet principal confié à l'étudiant..."
                            value={themeProjet}
                            onChange={(e) => setThemeProjet(e.target.value)}
                        />
                    </div>

                    <div className="group">
                        <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200 mb-2">
                            Objectifs assignés
                        </label>
                        <textarea
                            className="w-full pl-4 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 transition-all duration-200 bg-gray-50 hover:bg-white"
                            rows="3"
                            placeholder="Listez les objectifs principaux assignés à l'étudiant..."
                            value={objectifs}
                            onChange={(e) => setObjectifs(e.target.value)}
                        />
                    </div>

                    <div className="mt-6 flex justify-end">
                        <button
                            onClick={handleNextTab}
                            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
                        >
                            Suivant
                        </button>
                    </div>
                </div>
            </div>

            {/* Section Appréciations */}
            <div className={`${activeTab === 3 ? "block" : "hidden"}`}>
                <div className="bg-white rounded-lg p-6 mb-6 shadow-md border border-blue-100 hover:shadow-lg transition-shadow duration-300">
                    <h3 className="text-lg font-bold mb-5 text-blue-700 flex items-center">
                        APPRÉCIATIONS GLOBALES
                    </h3>

                    <div className="space-y-8">
                        {/* Appréciation 1 */}
                        <div className="group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200 mb-3">
                                1. Application dans ses activités
                            </label>
                            <div className="flex flex-wrap justify-between gap-2">
                                {[
                                    { label: "Paresseux", value: "1" },
                                    { label: "Le juste nécessaire", value: "2" },
                                    { label: "Bonne", value: "3" },
                                    { label: "Très forte ", value: "4" },
                                    { label: "Dépasse ses objectifs", value: "5" }
                                ].map((option) => (
                                    <div key={option.value} className="flex items-center">
                                        <input
                                            type="radio"
                                            name="application"
                                            value={option.value}
                                            checked={applicationValue === option.value}
                                            onChange={() => setApplicationValue(option.value)}
                                            className="form-radio text-blue-600 focus:ring-blue-500"
                                        />
                                        <span className="ml-2 text-sm text-gray-600">{option.label}</span>
                                    </div>
                                ))}
                            </div>
                        </div>

                        {/* Appréciation 2 */}
                        <div className="group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200 mb-3">
                                2. Ouverture aux autres
                            </label>
                            <div className="flex flex-wrap justify-between gap-2">
                                {[
                                    { label: "Isolé(e) ou en opposition", value: "1" },
                                    { label: "Renfermé(e) ou obtus", value: "2" },
                                    { label: "Bonne", value: "3" },
                                    { label: "Très Bonne", value: "4" },
                                    { label: "Excellente", value: "5" }
                                ].map((option) => (
                                    <div key={option.value} className="flex items-center">
                                        <input
                                            type="radio"
                                            name="ouverture"
                                            value={option.value}
                                            checked={ouvertureValue === option.value}
                                            onChange={() => setOuvertureValue(option.value)}
                                            className="form-radio text-blue-600 focus:ring-blue-500"
                                        />
                                        <span className="ml-2 text-sm text-gray-600">{option.label}</span>
                                    </div>
                                ))}
                            </div>
                        </div>
                        {/* Appréciation 3 */}
                        <div className="group">
                            <label className="block text-sm font-medium text-gray-700 group-hover:text-blue-600 transition-colors duration-200 mb-3">
                                3. Qualite de ses "Productions"
                            </label>
                            <div className="flex flex-wrap justify-between gap-2">
                                {[
                                    { label: "Médiocre", value: "1" },
                                    { label: "Acceptable", value: "2" },
                                    { label: "Bonne", value: "3" },
                                    { label: "Très Bonne", value: "4" },
                                    { label: "Très professionnelle", value: "5" }
                                ].map((option) => (
                                    <div key={option.value} className="flex items-center">
                                        <input
                                            type="radio"
                                            name="qualite"
                                            value={option.value}
                                            checked={qualiteValue === option.value}
                                            onChange={() => setQualiteValue(option.value)}
                                            className="form-radio text-blue-600 focus:ring-blue-500"
                                        />
                                        <span className="ml-2 text-sm text-gray-600">{option.label}</span>
                                    </div>
                                ))}
                            </div>
                        </div>

                        {/* Avis général sur le stagiaire */}
                        <div>
                            <label className="block text-sm font-medium text-gray-700">Avis général sur le stagiaire</label>
                            <textarea
                                className="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent mt-2"
                                rows="4"
                                placeholder="Donnez votre avis général sur le stagiaire et ses performances pendant le stage..."
                                value={avisGeneral} // Lier au state
                                onChange={(e) => setAvisGeneral(e.target.value)} // Mettre à jour le state
                            ></textarea>
                        </div>
                    </div>

                    <div className="mt-6 flex justify-end">
                        <button
                            onClick={handleNextTab}
                            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
                        >
                            Suivant
                        </button>
                    </div>
                </div>
            </div>

            {/* Section Compétences */}
            <div className={`${activeTab === 4 ? "block" : "hidden"} ${tabTransition}`}>
                <div className="bg-white rounded-lg p-6 mb-6 shadow-md border border-blue-100 hover:shadow-lg transition-shadow duration-300">
                    <h3 className="text-lg font-bold mb-5 text-blue-700 flex items-center">
                        COMPÉTENCES ACQUISES
                    </h3>

                    {/* Compétences individuelles */}
                    <h4 className="text-blue-600 font-medium mb-4">Compétences liées à l'individu</h4>
                    <table className="w-full table-auto border-collapse border border-gray-300 mb-6">
                        <thead>
                        <tr className="bg-blue-100">
                            <th className="px-4 py-2 text-left border border-gray-300">Compétence</th>
                            <th className="px-4 py-2 text-center border border-gray-300">NA</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Débutant</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome +</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Object.keys(competenceIndividu).map((comp, idx) => (
                            <tr key={idx}>
                                <td className="px-4 py-2 border border-gray-300">{comp}</td>
                                {["NA", "Débutant", "Autonome", "Autonome+"].map((level) => (
                                    <td key={level} className="px-4 py-2 border border-gray-300 text-center">
                                        <input
                                            type="radio"
                                            name={`competenceIndividu${idx}`}
                                            value={level}
                                            checked={competenceIndividu[comp] === level}
                                            onChange={() => {
                                                const newCompetences = { ...competenceIndividu };
                                                newCompetences[comp] = level;
                                                setCompetenceIndividu(newCompetences);
                                            }}
                                            className="form-radio"
                                        />
                                    </td>
                                ))}
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <div className="flex justify-between mb-4">
                        <span>Note globale compétences individuelles</span>
                        <input
                            type="number"
                            value={noteIndividu}
                            onChange={(e) => setNoteIndividu(e.target.value)}
                            className="w-20 px-4 py-2 border border-gray-300 rounded-lg text-center"
                            min="0"
                            max="20"
                        />
                    </div>

                    {/* Compétences liées à l'entreprise */}
                    <h4 className="text-blue-600 font-medium mb-4">Compétences liées à l'entreprise</h4>
                    <table className="w-full table-auto border-collapse border border-gray-300 mb-6">
                        <thead>
                        <tr className="bg-blue-100">
                            <th className="px-4 py-2 text-left border border-gray-300">Compétence</th>
                            <th className="px-4 py-2 text-center border border-gray-300">NA</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Débutant</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome +</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Object.keys(competenceEntreprise).map((comp, idx) => (
                            <tr key={idx}>
                                <td className="px-4 py-2 border border-gray-300">{comp}</td>
                                {["NA", "Débutant", "Autonome", "Autonome+"].map((level) => (
                                    <td key={level} className="px-4 py-2 border border-gray-300 text-center">
                                        <input
                                            type="radio"
                                            name={`competenceEntreprise${idx}`}
                                            value={level}
                                            checked={competenceEntreprise[comp] === level}
                                            onChange={() => {
                                                const newCompetences = { ...competenceEntreprise };
                                                newCompetences[comp] = level;
                                                setCompetenceEntreprise(newCompetences);
                                            }}
                                            className="form-radio"
                                        />
                                    </td>
                                ))}
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <div className="flex justify-between mb-4">
                        <span>Note globale compétences entreprise</span>
                        <input
                            type="number"
                            value={noteEntreprise}
                            onChange={(e) => {
                                const value = e.target.value;
                                if (value === "" || (Number(value) >= 0 && Number(value) <= 20)) {
                                    setNoteEntreprise(value);
                                }
                            }}
                            className="w-20 px-4 py-2 border border-gray-300 rounded-lg text-center"
                            min="0"
                            max="20"
                        />
                    </div>

                    {/* Compétences scientifiques */}
                    <h4 className="text-blue-600 font-medium mb-4">Compétences scientifiques</h4>
                    <table className="w-full table-auto border-collapse border border-gray-300 mb-6">
                        <thead>
                        <tr className="bg-blue-100">
                            <th className="px-4 py-2 text-left border border-gray-300">Compétence</th>
                            <th className="px-4 py-2 text-center border border-gray-300">NA</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Débutant</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Autonome +</th>
                        </tr>
                        </thead>
                        <tbody>
                        {Object.keys(competencesScientifiques).map((comp, idx) => (
                            <tr key={idx}>
                                <td className="px-4 py-2 border border-gray-300">{comp}</td>
                                {["NA", "Débutant", "Autonome", "Autonome+"].map((level) => (
                                    <td key={level} className="px-4 py-2 border border-gray-300 text-center">
                                        <input
                                            type="radio"
                                            name={`competenceScientifique${idx}`}
                                            value={level}
                                            checked={competencesScientifiques[comp] === level}
                                            onChange={() => {
                                                const newCompetences = { ...competencesScientifiques };
                                                newCompetences[comp] = level;
                                                setCompetencesScientifiques(newCompetences);
                                            }}
                                            className="form-radio"
                                        />
                                    </td>
                                ))}
                            </tr>
                        ))}
                        </tbody>
                    </table>
                    <div className="flex justify-between mb-4">
                        <span>Note globale compétences scientifiques</span>
                        <input
                            type="number"
                            value={noteScientifique}
                            onChange={(e) => {
                                const value = e.target.value;
                                if (value === "" || (Number(value) >= 0 && Number(value) <= 20)) {
                                    setNoteScientifique(value);
                                }
                            }}
                            className="w-20 px-4 py-2 border border-gray-300 rounded-lg text-center"
                            min="0"
                            max="20"
                        />
                    </div>

                    {/* Compétences Metiers */}

                    <h4 className="text-blue-600 font-medium mb-4">Compétences métier</h4>
                    <table className="w-full table-auto border-collapse border border-gray-300 mb-6">
                        <thead>
                        <tr className="bg-blue-100">
                            <th className="px-4 py-2 text-left border border-gray-300">Compétence</th>
                            <th className="px-4 py-2 text-center border border-gray-300">Niveau</th>
                        </tr>
                        </thead>
                        <tbody>
                        {/* Afficher les compétences existantes */}
                        {Object.keys(competenceMetier).map((comp, idx) => (
                            <tr key={idx}>
                                <td className="px-4 py-2 border border-gray-300">{comp}</td>
                                <td className="px-4 py-2 border border-gray-300">
                                    <input
                                        type="text"
                                        value={competenceMetier[comp]}
                                        onChange={(e) => {
                                            const newCompetences = { ...competenceMetier };
                                            newCompetences[comp] = e.target.value;
                                            setCompetenceMetier(newCompetences);
                                        }}
                                        className="w-full py-2 px-4 border border-gray-300 rounded-lg"
                                        placeholder="DEBUTANT, AUTONOME ou AUTONOME+"
                                    />
                                </td>
                            </tr>
                        ))}

                        {/* Ligne pour la nouvelle compétence */}
                        <tr>
                            <td className="px-4 py-2 border border-gray-300">
                                <input
                                    type="text"
                                    value={nouvelleCompetence}
                                    onChange={(e) => setNouvelleCompetence(e.target.value)}
                                    className="w-full py-2 px-4 border border-gray-300 rounded-lg"
                                    placeholder="Saisir une compétence métier..."
                                />
                            </td>
                            <td className="px-4 py-2 border border-gray-300">
                                <div className="flex space-x-2">
                                    <input
                                        type="text"
                                        value={nouveauNiveau}
                                        onChange={(e) => setNouveauNiveau(e.target.value)}
                                        className="w-full py-2 px-4 border border-gray-300 rounded-lg"
                                        placeholder="DEBUTANT, AUTONOME ou AUTONOME+"
                                    />
                                    <button
                                        type="button"
                                        onClick={ajouterCompetence}
                                        disabled={nouvelleCompetence.trim() === ""}
                                        className="bg-blue-500 text-white px-4 rounded-lg hover:bg-blue-600 transition-colors disabled:bg-gray-300"
                                    >
                                        Ajouter
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                        <div className="text-center mt-6">
                            <button
                                type="button"
                                onClick={handleSubmit}
                                className="bg-blue-500 text-white py-2 px-6 rounded-lg hover:bg-blue-600 transition-colors duration-200"
                            >
                                Envoyer l'évaluation
                            </button>
                        </div>
                    </div>
                </div>

            </div>

        );

}