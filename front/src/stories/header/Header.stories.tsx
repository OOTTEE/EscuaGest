import React from 'react';
import { ComponentStory, ComponentMeta } from '@storybook/react';
import 'antd/dist/antd.css'


import { MainHeader } from './MainHeader';

export default {
  title: 'Navbar/Header',
  component: MainHeader,
  parameters: {
    layout: 'fullscreen',
  },
} as ComponentMeta<typeof MainHeader>;

const Template: ComponentStory<typeof MainHeader> = (args) => <MainHeader {...args} />;

export const LoggedIn = Template.bind({});
LoggedIn.args = {
  user: {
    name: 'Jane Doe',
  },
};

export const LoggedOut = Template.bind({});
LoggedOut.args = {};
