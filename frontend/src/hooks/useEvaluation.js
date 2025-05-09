import { useState } from 'react';

export function useEvaluation() {
    const [formData, setFormData] = useState({
        nom: '',
        prenom: '',
        note: '',
        commentaires: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    return { formData, handleChange };
}
