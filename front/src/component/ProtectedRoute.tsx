import React from "react";
import {Navigate} from "react-router-dom";

type Props = {
    user: any,
    children?: React.ReactElement
}

export const ProtectedRoute = ({user, children}: Props) => {
    if (!user) {
        return <Navigate to={'/'} />
    } else {
        return <>
            {children}
        </>
    }
};
