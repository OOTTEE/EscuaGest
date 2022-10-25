import React from 'react';

import './header.css';
import {Button, Menu} from "semantic-ui-react";

type User = {
    name: string;
};

interface HeaderProps {
    user?: User;
    onLogin?: () => void;
    onLogout?: () => void;
}

export const Header = ({user, onLogin, onLogout}: HeaderProps) => {
    const isLogged = user != null;

    return (
        <Menu borderless pointing fixed='top'>
            <Menu.Item header>Escualos</Menu.Item>
            {isLogged ? <Menu.Item as='a'>Competiciones</Menu.Item> : ''}
            {isLogged ? <Menu.Item as='a'>Inscripciones</Menu.Item> : ''}
            {isLogged ? <Menu.Item as='a'>Administracion</Menu.Item> : ''}
            <Menu.Item position='right' as='a'>
                {isLogged ? <Menu.Item>{(isLogged) ? user.name : ''}</Menu.Item> : ''}
                {isLogged ? <Menu.Item as='a'>Profile</Menu.Item> : ''}
                {!isLogged ? <Menu.Item><Button as='a'>Sing In</Button></Menu.Item> : ''}
            </Menu.Item>
        </Menu>
    );
};
