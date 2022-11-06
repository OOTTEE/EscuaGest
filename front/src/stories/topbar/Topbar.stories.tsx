import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';
import 'antd/dist/antd.css'


import { TopBar } from './TopBar';

export default {
  title: 'Navbar/Header',
  component: TopBar,
  parameters: {
    layout: 'fullscreen',
  },
} as ComponentMeta<typeof TopBar>;

const Template: ComponentStory<typeof TopBar> = (args) => <TopBar {...args} />;

export const LoggedIn = Template.bind({});
LoggedIn.args = {
  user: {
    name: 'Jane Doe',
  },
};

export const LoggedOut = Template.bind({});
LoggedOut.args = {};
