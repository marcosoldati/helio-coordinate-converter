# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d.art3d as art3d

from mpl_toolkits.mplot3d import Axes3D
from matplotlib.patches import Wedge, Circle, PathPatch, FancyArrowPatch
from mpl_toolkits.mplot3d import proj3d

fig = plt.figure()
ax = fig.gca(projection='3d')
ax.set_aspect("equal")

# set axes scaling
ax.set_xlim3d(-4, 4)
ax.set_ylim3d(-4, 4)
ax.set_zlim3d(-4, 4)

# set 'camera' position
ax.azim = 14
ax.elev = 16
ax.dist = 5.5

plt.axis('off')

#draw sphere
def drawSphere(x=0,y=0,z=0,r=1):
    u, v = np.mgrid[0:2*np.pi:16j, 0:np.pi:11j]
    cx= x + np.cos(u)*np.sin(v) * r
    cy= y + np.sin(u)*np.sin(v) * r
    cz= z + np.cos(v) * r
    return ax.plot_wireframe(cx, cy, cz, color="grey", linestyle='dotted' )

drawSphere(r=2)

# draw Wedges
for c,i in enumerate(["x","y","z"]):
    #circle = Circle((0, 0), 2, alpha=0.1)
    circle = Wedge((0,0), 2, theta1=0.0, theta2=90.0, alpha=0.1*(c+1), facecolor='black')
    ax.add_patch(circle)
    art3d.pathpatch_2d_to_3d(circle, z=0, zdir=i)


#earth
drawSphere(10,0,0,0.5)


class Arrow3D(FancyArrowPatch):
    def __init__(self, xs, ys, zs, *args, **kwargs):
        FancyArrowPatch.__init__(self, (0,0), (0,0), *args, **kwargs)
        self._verts3d = xs, ys, zs
    
    def draw(self, renderer):
        xs3d, ys3d, zs3d = self._verts3d
        xs, ys, zs = proj3d.proj_transform(xs3d, ys3d, zs3d, renderer.M)
        self.set_positions((xs[0],ys[0]),(xs[1],ys[1]))
        FancyArrowPatch.draw(self, renderer)

ax.plot([0,10],[0,0],[0,0], linewidth=1, color='grey', linestyle='dotted')
x = Arrow3D([0,1],[0,0],[0,0], mutation_scale=20, lw=1, arrowstyle="-|>", color="k", label='x')
y = Arrow3D([0,0],[0,0],[0,1], mutation_scale=20, lw=1, arrowstyle="-|>", color="k", label='y')
z = Arrow3D([0,0],[0,1],[0,0], mutation_scale=20, lw=1, arrowstyle="-|>", color="k", label='z')

ax.add_artist(x)
ax.add_artist(y)
ax.add_artist(z)

# Labels
ax.text(0, 1.1, 0, 'x')
ax.text(0, 0, 1.1, 'y')
ax.text(1.1, -0.5, 0, 'z')
ax.text(10, -1, 0.5, 'Earth')
ax.text(0, -1.8, 1.8, 'Sun')

plt.show()